create database rent;
use rent;

create table fowner(
         fId varchar(3) primary key,
         fName varchar(10) not null,
         fPassword varchar(10) not null,
         fSuper int,   
         fPn varchar(20)  not null
);


create table customer(
         cId varchar(3) primary key,
         cName varchar(10) not null,
         cPassword varchar(10) not null,
         cSuper int,
         cPn varchar(20)  not null
         
);


create table house(
          hId varchar(10)   primary key,
          fId  varchar(3) not null,
          fName varchar(10) not null,
          cId varchar(10),
          dz varchar(50) not null,  
          Pri varchar(5) not null,
          hSta int not null,
          fPn  varchar(20) not null,
          foreign key (fId) references fowner (fId) 
         
         
);

insert into fowner values('000','admin','admin',1,'666666');
insert into fowner values('f01','zhangsan1','zhangsan1',2,'123456');
insert into fowner values('f02','zhangsan2','zhangsan2',2,'123456');
insert into fowner values('f03','zhangsan3','zhangsan3',2,'123456');
insert into fowner values('f04','张三','zhangsan',2,'123456');
insert into fowner values('f05','李四','lisi',2,'123456');


insert into  customer values('c01','lisi1','lisi1',2,'23456');  
insert into  customer values('c02','lisi2','lisi2',2,'23456');  
insert into  customer values('c03','lisi3','lisi3',2,'23456');  
insert into  customer values('c04','lisi4','lisi4',2,'23456');  


insert into house values('hA01','f01','zhangsan1','c01','湛江霞山绿塘路9号','5000',5,'123456');
insert into house values('hB01','f02','zhangsan2','c02','湛江霞山霞山鼎盛','12000',5,'123456');
insert into house values('hC01','f03','zhangsan3','c03','湛江霞山绿塘路11号','7000',5,'123456');
insert into house values('hC01','f03','张三','null','湛江赤坎区跃进路18号','6000',0,'123456');
insert into house values('hC01','f04','张三','null','湛江赤坎区寸金路9号','5000',0,'123456');
