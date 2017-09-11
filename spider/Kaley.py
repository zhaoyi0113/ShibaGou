from pandas import *
import numpy as np
import pandas as pd

for df in pd.read_csv('./Plants.csv', error_bad_lines=False, chunksize=10):

	myList = ['Record ID', 'stateProvince','decimalLatitude','decimalLongitude']

	df = df[myList]
	ndf = pd.DataFrame(columns=('Record ID', 'decimalLatitude', 'decimalLongitude'))

	for index, row in df.iterrows():
    		if row.loc['stateProvince'] == 'Victoria':
        		ndf.loc[len(ndf)] = row.loc['Record ID'], row.loc['decimalLatitude'], row.loc['decimalLongitude']
	with open('output.csv', 'a') as f:
		ndf.to_csv(f, header=False)
