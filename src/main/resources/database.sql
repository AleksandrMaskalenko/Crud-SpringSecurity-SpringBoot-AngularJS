CREATE TABLE author (
  id INT                NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)     NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE content (
  id INT                NOT NULL AUTO_INCREMENT PRIMARY KEY,
  bytes LONGBLOB        NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE song (
  id   INT              NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)     NOT NULL,
  author INT            NOT NULL,
  duration VARCHAR(255) NOT NULL,
  date VARCHAR(255)     NOT NULL,
  album VARCHAR(255)    NOT NULL,
  content INT           UNIQUE,

  FOREIGN KEY (content) REFERENCES content(id),
  FOREIGN KEY (author)  REFERENCES author(id)
)
  ENGINE = InnoDB;

CREATE TABLE user (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)     NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255)     NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE playlist (
  user_id INT NOT NULL,
  song_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (song_id) REFERENCES song (id)

)
  ENGINE = InnoDB;

INSERT INTO user VALUES (1,'John','admin','admin','ADMIN');
INSERT INTO user VALUES (2,'Liza','user','user','USER');

INSERT INTO author VALUES (1, 'Jay-Z');
INSERT INTO author VALUES (2, 'Gorillaz');
INSERT INTO author VALUES (3, 'Bruno Mars');
INSERT INTO author VALUES (4, 'AJR');
INSERT INTO author VALUES (5, 'Skrillex');

INSERT INTO song(id,name,author,duration,date,album)
VALUES (1,'99 Problems',1,'4:18','2003', 'The Black Album');

INSERT INTO song(id,name,author,duration,date,album)
VALUES (2,'Clint Eastwood',2,'4:29','2001', 'Gorillaz');

INSERT INTO song(id,name,author,duration,date,album)
VALUES (3,'That''s What I Like',3,'3:33','2016', '24K Magic');

INSERT INTO song(id,name,author,duration,date,album)
VALUES (4,'I''m Not Famous',4,'3:43','2016', 'What Everyone''s Thinking');

INSERT INTO song(id,name,author,duration,date,album)
VALUES (5,'“El Chapo”',5,'3:40','2015', 'The Documentary 2.5');

INSERT INTO song(id,name,author,duration,date,album)
VALUES (6,'Weak',4,'3:48','2016', 'What Everyone''s Thinking');

INSERT INTO song(id,name,author,duration,date,album)
VALUES (7,'Weak',4,'3:48','2016', 'What Everyone''s Thinking');


INSERT INTO playlist VALUES (2, 1);
INSERT INTO playlist VALUES (2, 4);
INSERT INTO playlist VALUES (2, 2);
INSERT INTO playlist VALUES (1, 1);
INSERT INTO playlist VALUES (1, 2);