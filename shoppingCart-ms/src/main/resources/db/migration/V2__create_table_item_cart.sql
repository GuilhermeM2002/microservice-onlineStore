create table item(
    id bigint not null auto_increment,
    cart_fk bigint not null,
    product_fk bigint not null,
    qt_product int not null,

    primary key(id)
);