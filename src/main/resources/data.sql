insert into chat (chat_id)
values (0), --  Sergii Stets with Pavel Burykh
       (1), --  Sergii Stets with Valeriia Stets
       (2), -- Sergii Stets with Andrii Chupyr
       (3), -- Pavel Burykh with Valeriia Stets
       (4), -- Pavel Burykh with Andrii Chupyr
       (5); -- Valeriia Stets with Andrii Chupyr

insert into user_contacts (user_id, contact_id)
values
    -- Sergii Stets contacts
    (42, 0), -- Pavel Burykh
    (42, 1), -- Valeriia Stets
    (42, 2), -- Andrii Chupyr

    -- Pavel Burykh contacts
    (0, 42), -- Sergii Stets
    (0, 1),  -- Valeriia Stets
    (0, 2),  -- Andrii Chupyr

    -- Valeriia Stets contacts
    (1, 42), -- Sergii Stets
    (1, 0),  -- Pavel Burykh
    (1, 2),  -- Andrii Chupyr

    -- Andrii Chupyr contacts
    (2, 42), -- Sergii Stets
    (2, 0),  -- Pavel Burykh
    (2, 1); -- Valeriia Stets

insert into user_chats
values
    --  Sergii Stets
    (42, 0), -- with Pavel Burykh
    (42, 1), -- with Valeriia Stets
    (42, 2), -- with Andrii Chupyr

    -- Pavel Burykh
    (0, 0),  -- with Sergii Stets
    (0, 3),  -- with Valeriia Stets
    (0, 4),  -- with Andrii Chupyr

    -- Valeriia Stets
    (1, 1),  -- with Sergii Stets
    (1, 3),  -- with Pavel Burykh
    (1, 5),  -- with Andrii Chupyr

    -- Andrii Chupyr
    (2, 2),  -- with Sergii Stets
    (2, 4),  -- with Pavel Burykh
    (2, 5); -- with Valeriia Stets

insert into chat_members(chat_id, member_id)
values
    --  Sergii Stets with Pavel Burykh
    (0, 42),
    (0, 0),

    --  Sergii Stets with Valeriia Stets
    (1, 42),
    (1, 1),

    -- Sergii Stets with Andrii Chupyr
    (2, 42),
    (2, 2),

    -- Pavel Burykh with Valeriia Stets
    (3, 0),
    (3, 1),

    -- Pavel Burykh with Andrii Chupyr
    (4, 0),
    (4, 2),

    -- Valeriia Stets with Andrii Chupyr
    (5, 1),
    (5, 2);

insert into chat_message (content,
                          chat_id,
                          author_id,
                          seen,
                          timestamp)
values
    -- Sergii Stets vs Pavel Burykh
    ('Hello', 0, 0, true, 1613035202),
    ('How are you?', 0, 0, true, 1613035203),
    ('I''m fine, thank you!', 0, 42, true, 1613035204),

    -- Vererii Stets vs Sergii Stets
    ('Hi', 1, 1, true, 1613035202),
    (':)', 1, 1, true, 1613035203),
    ('Hello', 1, 42, true, 1613035204),

    -- Andrii Chupyr vs Sergii Stets
    ('Hi, this messenger looks great. Good job!', 2, 2, true, 1613035202),
    ('Thank you', 2, 2, true, 1613035203),
    (':)', 2, 42, true, 1613035204),

    -- Valeriia Stets vs Pavel Burykh
    ('Hi', 3, 1, true, 1613035202),
    (':)', 3, 0, true, 1613035203),
    (':)', 3, 1, true, 1613035204),

    -- Pavel Burykh vs Andrii Chupyr
    ('Hi', 4, 2, true, 1613035202),
    (':)', 4, 0, true, 1613035203),
    (':)', 4, 2, true, 1613035204),

    -- Valeriia Stets vs Andrii Chupyr
    ('Hi', 5, 1, true, 1613035202),
    (':)', 5, 1, true, 1613035203),
    (':)', 5, 2, true, 1613035204);