SELECT account.account_id, c.fed_id, p.name
FROM account
INNER JOIN customer c on account.cust_id = c.cust_id
INNER JOIN product p on account.product_cd = p.product_cd
WHERE c.cust_type_cd = 'I'