# Concert Booking System
Concert Booking System เป็นระบบที่ออกแบบมาเพื่อจัดการการจองบัตรคอนเสิร์ตออนไลน์ โดยระบบประกอบด้วยบริการหลัก ๆ ที่เชื่อมต่อกันผ่าน Kafka ซึ่งมีรายละเอียดดังนี้:

#### UserService: รับผิดชอบการจัดการข้อมูลของผู้ใช้งาน เช่น การลงทะเบียนผู้ใช้ใหม่, และการจัดการโปรไฟล์ผู้ใช้.
#### OrderService: รับผิดชอบการจัดการคำสั่งซื้อบัตรคอนเสิร์ต รวมถึงการสร้างคำสั่งซื้อ, การยกเลิกคำสั่งซื้อ, และการอัปเดตสถานะคำสั่งซื้อ.
#### ConcertService: รับผิดชอบการจัดการคอนเสิร์ต โดยรับคำขอจองบัตรคอนเสิร์ตและจัดการสถานะของคำขอนั้น ๆ เช่น การยืนยันคำสั่งซื้อ, การยกเลิกคำสั่งซื้อ, และการอัปเดตสถานะคำสั่งซื้อ.
โดยทั้งสามบริการนี้มีการเชื่อมต่อกันผ่าน Kafka ซึ่งเป็นระบบ Message Broker ที่ช่วยในการสื่อสารระหว่างบริการต่าง ๆ อย่างมีประสิทธิภาพ

## โครงสร้างของระบบ
![image](https://imgur.com/Soqs3T8.jpg)

ระบบประกอบด้วยส่วนหลัก ๆ ดังนี้:
##### UserService: ใช้สำหรับจัดการผู้ใช้งาน
##### OrderService: ใช้สำหรับจัดการคำสั่งซื้อ
##### ConcertService: ใช้สำหรับจัดการคอนเสิร์ต
##### TrackingService: ใช้สำหรับจัดการจัดส่งและติดตาม
##### Searching Service: ใช้สำหรับการค้นหา
##### CatalogService: ใช้สำหรับแสดงข้อมูล
และมี Kafka ที่เป็นระบบ Message Broker ที่ใช้ในการสื่อสารระหว่างบริการ

## ขั้นตอนการทำงาน
#### UserService:ผู้ใช้ลงทะเบียนหรือเข้าสู่ระบบผ่าน UserService จะได้รับUserIdสำหรับทำธุรกรรมต่างๆ
#### UserService:จัดการข้อมูลผู้ใช้งานเช่น การสร้างโปรไฟล์ และการตรวจสอบการเข้าสู่ระบบ
#### OrderService:เมื่อผู้ใช้ทำการสร้างคำสั่งซื้อ คำสั่งซื้อจะถูกส่งไปยัง OrderService ผ่าน Kafka
#### OrderService : จัดการการสร้างคำสั่งซื้อ, การยืนยันคำสั่งซื้อ, และการยกเลิกคำสั่งซื้อ
#### ConcertService:เมื่อมีคำสั่งซื้อถูกสร้าง คำสั่งซื้อจะถูกส่งไปยัง ConcertService ผ่าน Kafka
#### ConcertService จัดการการจองบัตรคอนเสิร์ต รวมถึงการยืนยันคำสั่งซื้อและการยกเลิกคำสั่งซื้อ
## Dependencies
โปรเจกต์นี้มีการใช้งาน Kafka เพื่อการสื่อสารระหว่างบริการ ดังนั้นคุณจำเป็นต้องมี Kafka ติดตั้งและทำงานอย่างถูกต้อง

## วิธีการติดตั้งและใช้งาน
1. ดาวน์โหลดโปรเจกต์จาก GitHub Repository
2. ติดตั้ง Kafka และตั้งค่า Kafka Broker
3. แก้ไขค่า Kafka Broker ในการกำหนดค่าการเชื่อมต่อในโปรเจกต์
4. สร้างฐานข้อมูล MySQL และตารางตามโค้ดของ Entity
5. รันแต่ละบริการและทดสอบการทำงานของระบบ
## เพิ่มUser
   ![image](https://imgur.com/GsSEpJH.jpg)
## เพิ่มConcert
  ![image](https://imgur.com/VY6pU28.jpg)
## จองตั๋วด้วยUserId
  ![image](https://imgur.com/bZc1x4u.jpg)

## OrderProducerส่งmessaggeKafkaไปที่topic ConcertRequest
  ![image](https://imgur.com/HZYNSB8.jpg)
## ConcertService ทำการเช็คที่นั่งว่างแล้วทำการConfirmedหรือCancle Order
  ![image](https://imgur.com/z521hwQ.jpg)
  ![image](https://imgur.com/3Qu31fe.jpg)
  ![image](https://imgur.com/oq2BZif.jpg)
  ![image](https://imgur.com/zno87tN.jpg)

## จำนวนที่นั่งว่างในconcertก็จะลดลงตามorderที่confirm
![image](https://imgur.com/lT7nKu1.jpg)
![image](https://imgur.com/KHJvsUa.jpg)
![image](https://imgur.com/Q90nPhL.jpg)



