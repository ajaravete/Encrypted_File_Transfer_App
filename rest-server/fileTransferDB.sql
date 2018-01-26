DROP TABLE encrypted_file;
DROP TABLE user_account;

CREATE TABLE user_account (
  id            SERIAL PRIMARY KEY,
  email         TEXT UNIQUE,
  password_hash TEXT,
  name          TEXT,
  public_key    TEXT
);

CREATE TABLE encrypted_file (
  id        SERIAL PRIMARY KEY,
  sender    TEXT REFERENCES user_account (email),
  receiver  TEXT REFERENCES user_account (email),
  file_name TEXT,
  file_data BYTEA
);

-- INSERT INTO user_account (email, password_hash, name, public_key) VALUES ('alex1@test.com', 'dsad', 'alex1', 'BASE64');
-- INSERT INTO user_account (email, password_hash, name, public_key) VALUES ('alex2@test.com', 'dsad', 'alex2', 'BASE64');

-- INSERT INTO encrypted_file (sender, receiver,file_name, file_data) VALUES ('alex1@test.com', 'alex2@test.com','test.html', 'some_data1');
-- INSERT INTO encrypted_file (sender, receiver,file_name, file_data) VALUES ('alex2@test.com', 'alex1@test.com','test.html', 'some_data2');

-- SELECT *
-- FROM user_account;

-- SELECT
--   email,
--   name,
--   public_key
-- FROM user_account
-- WHERE id = 1;

-- SELECT
--   id,
--   sender,
--   receiver,
--   file_name
-- FROM encrypted_file;

-- SELECT *
-- FROM encrypted_file;