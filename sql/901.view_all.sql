CREATE OR REPLACE VIEW all_data_view as
SELECT t.id as type_id, t.type
        , c.id as country_id, c.code as country_code, c.country, c.country_ext, c.country_eng, c.country_eng_ext
        , s.id as state_id, s.state
        , ct.id as city_id, ct.city
        , a.id as address_id, a.zipcode, a.old_zipcode, a.address
        , t.update_date as type_update_date
        , c.update_date as country_update_date
        , s.update_date as state_update_date
        , ct.update_date as city_update_date
        , a.update_date as address_update_date
FROM address a
LEFT JOIN city ct on ct.id = a.city_id
LEFT JOIN state s on s.id = ct.state_id
LEFT JOIN country c on c.id = s.country_id
LEFT JOIN type t on t.id = s.type_id and ct.type_id = t.id and a.type_id = t.id
