import json
import urllib.request
from pandas import *
import numpy as np
import pandas as pd
import requests
import urllib
import time
import sys

name_cache=[]

def download_file(url, name):
    print('download ' + url)
    f = open('images/' + name, 'wb')
    f.write(requests.get(url).content)
    f.close()

def request_image_info(json_data):
    guid = json_data['guid']
    name = json_data['name']
    family = json_data['family']
    common_name = json_data['commonName']
    count = json_data['count']
    rank = json_data['rank']
    kingdom = json_data['kingdom']
    lat = json_data['lat']
    lon = json_data['lon']
    locations = json_data['locations']
    image_id = time.time() 
    if name in name_cache:
      return
    name_cache.append(name)
    image_res = urllib.request.urlopen('http://bie.ala.org.au/ws/imageSearch/' + guid)
    image_json_data = json.load(image_res)
    results = image_json_data['searchResults']['results']
    row_data = pd.DataFrame(columns=['id','name','commonName','guid','kingdom','family','count','rank', 'description','latitude','longtitude', 'imageUrl', 'thumbnailUrl', 'Locations'])
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
            row_data.loc[0] = [image_id, name, common_name, guid, kingdom,family,count,rank,info,lat,lon,image_url,thumbnail_url, locations]
    print('write '+name)    
    row_data.to_csv(sys.argv[2]+'/ImageInfo.csv', mode='a', header=True, sep=',', encoding='utf-8',index=False)

def getImageCounts():
    res = urllib.request.urlopen('http://biocache.ala.org.au/ws/explore/counts/group/Plants.json')
    json_data = json.load(res)
    return json_data[1]

def getImageInfoIndex(count):
    res = urllib.request.urlopen('https://biocache.ala.org.au/ws/explore/group/Plants.json?lat=-37.830112&lon=144.976699&pageSize='+ str(count) + '&radius=300')
    json_data = json.load(res)
    return json_data

if len(sys.argv) < 3:
  print("please specify input file and output folder")
  exit(-1)

inputFile = sys.argv[1]
outputFolder = sys.argv[2]
print('input ', inputFile, ' output ', outputFolder)
imageCounts = getImageCounts()
print('get image counts ', imageCounts)
imageIndex = getImageInfoIndex(imageCounts)
print('get image index ', imageIndex)
allImageLocations = pd.read_csv(inputFile, error_bad_lines=False, header=None, sep='\t')
notFound = []
filteredImages = []
locations = pd.DataFrame(columns=('latitude', 'longtitude'))
for index in imageIndex:
    matched = allImageLocations.loc[allImageLocations[4] == index['name']]
    if len(matched.index) == 0:
      print('not found ', index)
      notFound.append(index)
    elif index['name'] not in name_cache:
      print('query plant ' + index['name'])
      index['lat'] = matched.iloc[0][3]
      index['lon'] = matched.iloc[0][4]
      locationLen = locations.shape[0]
      matchedLocLen = matched.shape[0]
      locStr = '('
      for i in range(matchedLocLen):
          locStr += str(i+locationLen)
          if i < matchedLocLen:
            locStr += ' '
      locStr += ')'
      index['locations'] = '<Locations>' + locStr
      filteredImages.append(index)
      request_image_info(index)
      for i, row in matched.iterrows():
          locations.loc[len(locations)] = row.loc[2], row.loc[3]
      locations.to_csv(sys.argv[2]+'/Locations.csv', mode='a', header=False, sep=',', encoding='utf-8',index=False)

print('total not found', len(notFound))
print('total images', len(filteredImages))

# for index, row in allImageLocations.iterrows():
#         try:
          
#         except:
#           pass