
DROP TABLE IF EXISTS public.product CASCADE;
CREATE TABLE public.product (
    id serial NOT NULL PRIMARY KEY,
    name text,
    price float4,
    description text,
    supplier_id integer NOT NULL,
    product_category_id integer NOT NULL
);


DROP TABLE IF EXISTS public.supplier CASCADE;
CREATE TABLE public.supplier (
    id serial NOT NULL PRIMARY KEY,
    name text,
    description text
);

DROP TABLE IF EXISTS public.product_category CASCADE;
CREATE TABLE public.product_category (
    id serial NOT NULL PRIMARY KEY,
    name text,
    department text,
    description text
);

DROP TABLE IF EXISTS public.shop_user CASCADE;
CREATE TABLE public.shop_user (
    id serial NOT NULL PRIMARY KEY,
    user_name text,
    password text,
    first_name text,
    last_name text,
    email text,
    city text,
    address text,
    phone_number integer
);

DROP TABLE IF EXISTS public.cart CASCADE;
CREATE TABLE public.cart (
    id serial NOT NULL PRIMARY KEY,
    user_id integer NOT NULL,
    product_id integer NOT NULL,
    amount integer
);




ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id) ON DELETE CASCADE;

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_category_id) REFERENCES public.product_category(id) ON DELETE CASCADE;

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.shop_user(id) ON DELETE CASCADE;

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES public.product(id) ON DELETE CASCADE;


INSERT INTO supplier (name, description)
VALUES ('McDonalds', 'Good feelings and strength.'),
       ('Unicum', 'Average feelings.'),
       ('Gucci', 'Narcist feelings.');

INSERT INTO product_category(name, department, description)
VALUES ('Bad', 'Emotion', 'You lose spirit energy using bad feelings.'),
       ('Good', 'Emotion', 'Good emotions upload your spiritual energy');

INSERT INTO product (name, price, description, supplier_id, product_category_id)
VALUES ('Fear', 9.9, 'Feel intense fear for an hour.', 2, 1),
       ('Narcism', 79.9, 'Feel like you are the best for an hour.', 3, 2),
       ('Amusement', 12.9, 'Be light-headed for an hour.',2 , 2),
       ('Drunkeness', 24.9, 'Get drunk for an hour without hangover and health problems.', 2, 2),
       ('Active', 13.9, 'Get the power for a while.', 1, 2),
       ('Feel cold', 9.9, 'Feel the cold for an hour.', 2, 1),
       ('Coma', 19.9, 'Fall asleep instant for 2 hour, nothing can wake up you for a while.', 2, 2),
       ('Jerk', 9.9, 'Say what you want and do what you want. No inhibition for an hour.', 3, 1),
       ('Average', 9.9, 'Feel like just an average random people.', 1, 1),
       ('Rich', 3.9, 'Feel like rich person and spend your money without gilt (for an hour).', 3, 2),
       ('Feminine', 20, 'Feel like a pretty women for a day.', 3, 1),
       ('Green', 3.9, 'Feel like you carry for the nature. (4 hour)', 1, 1),
       ('Anxiety', 13.9, 'Feel inertia and despair for a day.', 1, 1);


