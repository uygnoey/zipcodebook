CREATE OR REPLACE VIEW all_data_view as
SELECT c.id as country_id, c.code as country_code, c.country, c.country_ext, c.country_eng, c.country_eng_ext, c.update_date as country_update_date
        , s.id as state_id, s.state, s.state_ext, s.state_eng, s.state_eng_ext, s.update_date as state_update_date
        , ct.id as city_id, ct.city, ct.city_ext, ct.city_eng, ct.city_eng_ext, ct.update_date as city_update_date
        , a.id, a.zipcode, a.old_zipcode, a.address, a.address_ext, a.address_eng, a.address_eng_ext, a.update_date as address_update_date
FROM country c
LEFT JOIN state s on c.id = s.country_id
LEFT JOIN city ct on s.id = ct.state_id
LEFT JOIN address a on ct.id = a.city_id
