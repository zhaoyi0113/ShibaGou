import json
import urllib.request
from pandas import *
import numpy as np
import pandas as pd
import requests
import urllib
import time

name_cache=[]

def download_file(url, name):
    print('download ' + url)
    f = open('images/' + name, 'wb')
    f.write(requests.get(url).content)
    f.close()

def request_image_info(lat, lon):
    res = urllib.request.urlopen('https://biocache.ala.org.au/ws/explore/group/Plants.json?lat='+str(lat)+'&lon='+str(lon)+'&pageSize=1&radius=1')
    json_data = json.load(res)
    guid = json_data[0]['guid']
    name = json_data[0]['name']
    family = json_data[0]['family']
    common_name = json_data[0]['commonName']
    count = json_data[0]['count']
    rank = json_data[0]['rank']
    kingdom = json_data[0]['kingdom']
    image_id = time.time() 
    if name in name_cache:
      return
    name_cache.append(name)
    image_res = urllib.request.urlopen('http://bie.ala.org.au/ws/imageSearch/' + guid)
    image_json_data = json.load(image_res)
    results = image_json_data['searchResults']['results']
    row_data = pd.DataFrame(columns=['id','name','commonName','guid','kingdom','family','count','rank', 'description','latitude','longtitude', 'imageUrl', 'thumbnailUrl'])
    image_url = ''
    thumbnail_url = ''
    if len(results) > 0:
        image_url = results[0]['imageUrl']
        thumbnail_url = results[0]['thumbnailUrl']
        # download_file(image_url, name)
        # download_file(thumbnail_url, name + '-thumbnail')
    else:
        return
    
    wiki_res = urllib.request.urlopen("https://en.wikipedia.org/w/api.php?action=query&format=json&titles=" + urllib.parse.quote(name) + "&explaintext=&prop=extracts")
    plant_info = json.load(wiki_res)
    for key in plant_info['query']['pages']:
        if 'extract' in plant_info['query']['pages'][key]:
            info = plant_info['query']['pages'][key]['extract']
            row_data.loc[0] = [image_id, name, common_name, guid, kingdom,family,count,rank,info,lat,lon,image_url,thumbnail_url]
    print('write '+name)    
    row_data.to_csv('result.csv', mode='a', header=False, sep='\t', encoding='utf-8')

for df in pd.read_csv('./output.csv', error_bad_lines=False, chunksize=10, header=None):
    for index, row in df.iterrows():
        try:
          request_image_info(row[2], row[3])
        except:
          pass

