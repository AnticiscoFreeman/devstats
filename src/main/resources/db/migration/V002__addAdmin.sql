INSERT INTO account (id, active, name, password, username)
VALUES (1, true, 'root', '$2a$08$Dr48qb/mLw.wNpmyWFxFCe72dcY0ciJDuCCb8l7A0bqnbab39vmLi', 'root');

INSERT INTO account_roles (account_id, roles)
VALUES (1, 'ADMIN');