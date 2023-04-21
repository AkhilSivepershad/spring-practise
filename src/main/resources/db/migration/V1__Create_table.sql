CREATE SEQUENCE sensitive_word_id_seq;

CREATE TABLE sensitive_word (
  id INTEGER DEFAULT nextval('sensitive_word_id_seq'::regclass) NOT NULL,
  word VARCHAR NOT NULL,
  create_time TIMESTAMP WITHOUT TIME ZONE,
  PRIMARY KEY (id)
);
