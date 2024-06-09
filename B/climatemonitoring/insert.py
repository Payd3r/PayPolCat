import pandas as pd
import psycopg2
from io import StringIO

# Carica il file Excel
file_path = 'C:\\Users\\paolo\\Desktop\\PayPolCat\\B\\climatemonitoring\\Data\\geonames-and-coordinates.xlsx'
data = pd.read_excel(file_path)

# Estrazione dei dati
data.columns = ['id', 'name', 'name_ascii', 'country_code', 'country_name', 'lat_lon']
data[['lat', 'lon']] = data['lat_lon'].str.split(', ', expand=True)
data = data.drop(columns=['lat_lon'])

# Converti il DataFrame in un CSV
csv_buffer = StringIO()
data.to_csv(csv_buffer, index=False, header=False)
csv_buffer.seek(0)

# Connessione al database
conn = psycopg2.connect(
    dbname="bvgq1krz8lvxl3imqz0z",
    user="uwrddkjoepyde275arvo",
    password="sx9wxithD6BeqC1nCYbaUpETEHlqtJ",
    host="bvgq1krz8lvxl3imqz0z-postgresql.services.clever-cloud.com",
    port="50013"
)
cursor = conn.cursor()

# Utilizza la funzione COPY per inserire i dati
copy_query = """
COPY coordinatemonitoraggio (id, name, name_ascii, country_code, country_name, lat, lon)
FROM stdin WITH CSV
"""

cursor.copy_expert(copy_query, csv_buffer)

# Commit delle modifiche e chiusura della connessione
conn.commit()
cursor.close()
conn.close()
