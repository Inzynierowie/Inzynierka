import datetime
import radar 
import random
import json
from random import randint

print("Random Insert Generator for SQL")
print("1.Patients")
print("2.Doctors")
print("3.Appointments")
print("4.PatientMedData")
print("5.MedicalFacility")
print("6.PriceList")
choice = int(input("Select: ")) 
iterator = int(input("How Many Records?: "))

#Create/open and erase data from file
tablefile= open("data.txt","w+")
tablefile.truncate(0)

#RandomDateGenerator
def randomDateGen():
    randdate = radar.random_datetime(
    start = datetime.datetime(year=2000, month=5, day=24),
    stop = datetime.datetime(year=2020, month=5, day=24)
    )
    randdate = str(randdate)
    size = len(randdate)
    mod_randdate = randdate[:size - 9]
    return mod_randdate

#RandomNumberGenerator this time for phone number
def randomWithNDigits(n):
    range_start = 10**(n-1)
    range_end = (10**n)-1
    return randint(range_start, range_end)    
    
names = []
surnames = []
specializations = []
medicalProcedures = []
medicalFacilityName = []
localizations = []
description = []

with open("insert_data.json") as data:
    data=json.load(data)

#fill data from JSON 
for x in data["names"]:
    names.append(x)

for x in data["surnames"]:
    surnames.append(x)

for x in data["specializations"]:
    specializations.append(x)

for x in data["medicalProcedures"]:
    medicalProcedures.append(x)

for x in data["medicalFacilityName"]:
    medicalFacilityName.append(x)

for x in data["localizations"]:
    localizations.append(x)

for x in data["description"]:
    description.append(x)

#Insert Operations
if (choice == 1):
    for i in range(iterator):
        insertOperatorion = "INSERT INTO patients (name,surname,insured) VALUES (" + \
            "'"+ random.choice(names) + "', '"+ random.choice(surnames) + "', " + random.choice(['TRUE', 'FALSE']) +");"
        tablefile.write(insertOperatorion)
        tablefile.write("\n")

elif (choice == 2):
    for i in range(iterator):
        insertOperatorion = "INSERT INTO doctors (name,surname,specialist) VALUES (" +  \
            "'" + random.choice(names) + "', '"+random.choice(surnames) + "', '" +random.choice(specializations) +"');"
        tablefile.write(insertOperatorion)
        tablefile.write("\n")
elif (choice == 3):
    patientsmax = int(input("How Many patients: "))
    docmax = int(input("How Many doctors: "))
    for i in range(iterator):
        date = randomDateGen()
        insertOperatorion = "INSERT INTO appointments (appointment_date,cost,patient_id,doctor_id) VALUES (" +  \
            "'" + date + "'," +str(random.randint(100, 9999)) + "," + str(random.randint(1, patientsmax)) + "," + str(random.randint(1, docmax)) + ");"
        tablefile.write(insertOperatorion)
        tablefile.write("\n")
elif (choice == 4):
    patientsmax = int(input("How Many patients: ")) 
    docmax = int(input("How Many doctors: "))
    for i in range(iterator):
        date = randomDateGen()
        insertOperatorion = "INSERT INTO Patients_med_data (patient_id,doctor_id,treatment_date,additional_notes,medical_procedure) VALUES (" +  \
            "" + str(random.randint(1, patientsmax)) + "," + str(random.randint(1, docmax)) + ",'" + date + "','" +random.choice(medicalProcedures)+ "','" +random.choice(description) + "');"
        tablefile.write(insertOperatorion)
        tablefile.write("\n")
elif (choice == 5):
    patientsmax = int(input("How Many patients: ")) 
    docmax = int(input("How Many doctors: "))
    for i in range(iterator):
        phonenumber = randomWithNDigits(9)
        insertOperatorion = "INSERT INTO medical_facility (name,localization,doctor_count,patient_count,contact_number) VALUES (" +  \
            "'"+random.choice(medicalFacilityName) + "', '"+random.choice(localizations) + "', " +str(random.randint(1, docmax))+ ", " +str(random.randint(1, patientsmax))+ ", " +str(phonenumber) +");"
        tablefile.write(insertOperatorion)
        tablefile.write("\n")
elif (choice == 6):
    for i in range(iterator):
        insertOperatorion = "INSERT INTO price_list (treatment,price) VALUES (" +  \
            "'" + str(random.choice(medicalProcedures)) + "', "+ str(random.randint(100, 9999)) +");"
        tablefile.write(insertOperatorion)
        tablefile.write("\n")
else:
    print("Wrong table selected.")