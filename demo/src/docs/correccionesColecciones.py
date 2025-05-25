import json
"""
with open("Médico.json", encoding="utf-8") as f:
    data = json.load(f)

for doc in data:
    
    doc["numero_documento"]= str(doc["numero_documento"])
    doc["num_registro"]= str(doc["num_registro"])

ids = [registro["_id"]["$oid"] for registro in data]


with open("Medicos.json", "w", encoding="utf-8") as f:
    json.dump(data, f, indent=2, ensure_ascii=False)

print("ids medicos")
print(", ".join(ids))
print()


with open("IPSs.json", "r") as f:
    datai = json.load(f)

# Corrige los médicos
for ips in datai:
    ips["NIT"] = str(ips["NIT"])
    ips_id = ips["_id"]
    if isinstance(ips_id, str) and ips_id.startswith("{'$oid':"):
        # Convierte el string a un dict
        ips["_id"] = {"$oid": ips_id[9:-2]}
    

ips = [registro["_id"]["$oid"] for registro in datai]


with open("IPS.json", "w") as f:
    json.dump(datai, f, indent=2)

print("ids ips")
print(", ".join(ips))
print()

with open('Afiliado.json', 'r') as file:
    dataa = json.load(file)

# Convertir fechas
for doc in dataa:
    # Convertir fecha_nacimiento
    doc["fecha_nacimiento"] = {"$date": doc["fecha_nacimiento"] + "T00:00:00Z"}
    
    doc["numero_documento"] = str(doc["numero_documento"])

    # Convertir fechas dentro de ordenesServicio
    for orden in doc.get("ordenesServicios", []):
        orden["fecha"] = {"$date": orden["fecha"] + "T00:00:00Z"}       

    if isinstance(doc.get("parentesco"), dict) and "$oid" in doc["parentesco"]:
        doc["parentesco"] = doc["parentesco"]["$oid"]


ordenes = []   
afi = [registro["_id"]["$oid"] for registro in dataa]

for registro in dataa: 
    for orden in registro["ordenesServicios"]:
        id = orden["_id"]
        if id not in ordenes:
            ordenes.append(str(id))
# Guardar archivo listo para importar a MongoDB
with open('Afiliados.json', 'w') as file:
    json.dump(dataa, file, indent=2)


print("ids afiliados")
print(", ".join(afi))
print()
print("ids ordenes")
print(", ".join(ordenes))
print()

"""
import random
from datetime import datetime, time, date, timezone
with open("Servicio.json", 'r', encoding='utf-8') as f:
    datas = json.load(f)

for item in datas:
    if 'prestaciones' in item:
        for prestacion in item['prestaciones']:
            fecha_str = prestacion.get('fecha')
            if fecha_str and isinstance(fecha_str, str) and len(fecha_str) == 10:
                try:
                    
                    fecha_obj = datetime.strptime(fecha_str, '%Y-%m-%d')
                    hora_aleatoria = random.randint(8, 20)
                    minuto_aleatorio = random.randint(0, 59)
                    fecha_con_hora = datetime.combine(
                        fecha_obj, time(hora_aleatoria, minuto_aleatorio)
                    ).replace(tzinfo=timezone.utc)

                    prestacion['fecha'] = {"$date": fecha_con_hora.isoformat()}

                    # Comparar correctamente con la hora actual en UTC
                    ahora_utc = datetime.now(timezone.utc)
                    prestacion['finalizado'] = fecha_con_hora < ahora_utc 
                    
                    if prestacion.get('afiliado') is None:
                        prestacion['orden'] = None

                except ValueError:
                    print(f"Advertencia: La fecha '{fecha_str}' no tiene el formato YYYY-MM-DD y se ignorará.")
                    continue

with open("Servicios.json", 'w', encoding='utf-8') as f:
    json.dump(datas, f, indent=2, ensure_ascii=False)
