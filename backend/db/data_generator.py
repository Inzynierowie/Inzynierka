import datetime
import radar 
import random
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
index=int(input("Last index in this table: ")) + 1 

#Create/open and erase data from file
tablefile= open("data.txt","w+")
tablefile.truncate(0)

def randomDateGen():
    randdate = radar.random_datetime(
    start = datetime.datetime(year=2000, month=5, day=24),
    stop = datetime.datetime(year=2013, month=5, day=24)
    )
    randdate = str(randdate)
    size = len(randdate)
    mod_randdate = randdate[:size - 9]
    return mod_randdate

def random_with_N_digits(n):
    range_start = 10**(n-1)
    range_end = (10**n)-1
    return randint(range_start, range_end)    
    
#Tables with rand data

names = [
    "Liam","Emma","Noah","Olivia","William","Ava","James","Isabella",
    "Oliver","Sophia","Benjamin","Charlotte","Elijah","Mia","Lucas",
    "Amelia","Mason","Harper","Logan","Evelyn"
    ]
surnames = ["Smith","Johnson","Williams","Brown","White","Jackson","Thomson","Robinson","Grimes"]
specializations = ["Plastic surgery","Orthopedics","Cardiology","Radiology","Dermatology"]
medicalProcedures = [
    "Appendectomy","Breast biopsy","Carotid endarterectomy","Cataract surgery",
    "Cesarean section","Cholecystectomy","Coronary artery bypass","Debridement of wound"
    ]
medicalFacilityName = ["Hospital","Clinic"]
localizations = ["Berlin","London","France","Warsaw","New York","Rio"]
description = ["remove lung","didnt pay in time","psycho"]

if (choice == 1):
    for i in range(iterator):
        insertOperatorion = "INSERT INTO patients (id,name,surname,insured) VALUES (" + str(index) \
             + ", '"+random.choice(names) + "', '"+random.choice(surnames) + "', " + random.choice(['TRUE', 'FALSE']) +");"
        tablefile.write(insertOperatorion)
        tablefile.write("\n")
        index += 1

elif (choice == 2):
    for i in range(iterator):
        insertOperatorion = "INSERT INTO doctors (id,name,surname,specialist) VALUES (" + str(index) \
             + ", '"+random.choice(names) + "', '"+random.choice(surnames) + "',' " +random.choice(specializations) +"');"
        tablefile.write(insertOperatorion)
        tablefile.write("\n")
        index += 1
elif (choice == 3):
    patientsmax = int(input("How Many patients: "))
    docmax = int(input("How Many doctors: "))
    for i in range(iterator):
        date = randomDateGen()
        insertOperatorion = "INSERT INTO appointments (id, appointment_date, cost,patient_id, doctor_id) VALUES (" + str(index) \
            + ",'" + date + "'," +str(random.randint(100, 9999)) + "," + str(random.randint(1, patientsmax)) + "," + str(random.randint(1, docmax)) + ");"
        tablefile.write(insertOperatorion)
        tablefile.write("\n")
        index += 1
elif (choice == 4):
    patientsmax = int(input("How Many patients: ")) 
    docmax = int(input("How Many doctors: "))
    for i in range(iterator):
        date = randomDateGen()
        insertOperatorion = "INSERT INTO Patients_med_data (id, patient_id, doctor_id, treatment_date,additional_notes, medical_procedure) VALUES (" + str(index) \
            + "," + str(random.randint(1, patientsmax)) + "," + str(random.randint(1, docmax)) + ",'" + date + "','" +random.choice(medicalProcedures)+ "','" +random.choice(description) + "');"
        tablefile.write(insertOperatorion)
        tablefile.write("\n")
        index += 1
elif (choice == 5):
    patientsmax = int(input("How Many patients: ")) 
    docmax = int(input("How Many doctors: "))
    for i in range(iterator):
        phonenumber = random_with_N_digits(9)
        insertOperatorion = "INSERT INTO medical_facility (id,name,localization,doctor_count,patient_count,contact_number) VALUES (" + str(index) \
             + ", '"+random.choice(medicalFacilityName) + "', '"+random.choice(localizations) + "', " +str(random.randint(1, docmax))+ ", " +str(random.randint(1, patientsmax))+ ", " +str(phonenumber) +");"
        tablefile.write(insertOperatorion)
        tablefile.write("\n")
        index += 1
elif (choice == 6):
    for i in range(iterator):
        insertOperatorion = "INSERT INTO price_list (id,treatment,price) VALUES (" + str(index) \
             + ", '"+random.choice(medicalProcedures) + "', "+str(random.randint(100, 9999)) +");"
        tablefile.write(insertOperatorion)
        tablefile.write("\n")
        index += 1
else:
    print("Wrong table selected.")