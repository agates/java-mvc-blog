USE ATP_Blog;

DROP TABLE CONTENT;
DROP TABLE LOGIN;

CREATE TABLE LOGIN (
	username VARCHAR(32) PRIMARY KEY,
	password VARCHAR(128) NOT NULL
);

INSERT INTO LOGIN (username, password) VALUES('agates', 'password');

CREATE TABLE CONTENT (
	id UNIQUEIDENTIFIER NOT NULL PRIMARY KEY DEFAULT NEWID(),
	username VARCHAR(32) NOT NULL,
	title VARCHAR(64) NULL DEFAULT NULL,
	content NVARCHAR(MAX) NOT NULL,
	parent_id UNIQUEIDENTIFIER NULL DEFAULT NULL,
	created DATETIME NOT NULL DEFAULT SYSDATETIME(),
	FOREIGN KEY (username) REFERENCES LOGIN(username),
	FOREIGN KEY (parent_id) REFERENCES CONTENT(id)
);

INSERT INTO CONTENT (username, title, content, parent_id)
	VALUES('agates', 'My Cool Blog', 'This is a blog with a very short amount of text.', NULL);
INSERT INTO CONTENT (username, title, content, parent_id)
	VALUES ('agates', 'Blog Cool My', 'Less text here.', NULL);
INSERT INTO CONTENT (username, title, content, parent_id)
	SELECT 'agates', NULL, 'Wow, nice blog!', id FROM CONTENT WHERE parent_id IS NULL;
INSERT INTO CONTENT (username, title, content, parent_id)
	SELECT 'agates', NULL, 'Comment, nice wow!', id FROM CONTENT WHERE parent_id IS NOT NULL;
INSERT INTO CONTENT (username, title, content, parent_id)
	SELECT 'agates', NULL, 'Someone let the comments out', id FROM CONTENT WHERE parent_id IS NOT NULL;
INSERT INTO CONTENT (username, title, content, parent_id)
	SELECT 'agates', NULL, 'Energy drinks are good', id FROM CONTENT WHERE parent_id IS NOT NULL;
INSERT INTO CONTENT (username, title, content, parent_id)
	SELECT 'agates', NULL, 'I should have quite a few comments now.', id FROM CONTENT WHERE parent_id IS NOT NULL;