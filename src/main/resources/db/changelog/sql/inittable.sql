CREATE TABLE Player (
                        id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                        name VARCHAR(255),
                        profession VARCHAR(255),
                        auditor_id BIGINT,
                        bank_debt INT,
                        bank_loan INT,
                        children INT,
                        child_expenses INT,
                        FOREIGN KEY (auditor_id) REFERENCES Player(id)
);

CREATE TABLE RealEstate (
                            id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                            name VARCHAR(255),
                            income INT,
                            first_payment INT,
                            price INT,
                            mortgage INT,
                            player_id BIGINT,
                            FOREIGN KEY (player_id) REFERENCES Player(id)
);

CREATE TABLE Stock (
                       id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                       name VARCHAR(255),
                       income INT,
                       first_payment INT,
                       price INT,
                       mortgage INT,
                       player_id BIGINT,
                       FOREIGN KEY (player_id) REFERENCES Player(id)
);

CREATE TABLE Business (
                          id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                          name VARCHAR(255),
                          income INT,
                          first_payment INT,
                          price INT,
                          mortgage INT,
                          player_id BIGINT,
                          FOREIGN KEY (player_id) REFERENCES Player(id)
);