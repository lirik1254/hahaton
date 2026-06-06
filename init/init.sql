CREATE TABLE IF NOT EXISTS auth(
    login varchar(255),
    hash_pass varchar(64) NOT NULL,
    PRIMARY KEY (login)
);

CREATE TABLE IF NOT EXISTS device(
    id UUID PRIMARY KEY,
    building_id UUID not null
);

CREATE TABLE IF NOT EXISTS "group"(
    id UUID PRIMARY KEY,
    name varchar(255) not null
);

CREATE TABLE IF NOT EXISTS device_group (
    device_id UUID NOT NULL REFERENCES device(id)  ON DELETE CASCADE,
    group_id  UUID NOT NULL REFERENCES "group"(id) ON DELETE CASCADE,
    PRIMARY KEY (device_id, group_id)
);

CREATE TABLE template (
    id UUID PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE widget (
    id UUID PRIMARY KEY,
    name varchar(255) NOT NULL,
    x INTEGER NOT NULL,
    y INTEGER NOT NULL,
    width  INTEGER NOT NULL,
    height INTEGER NOT NULL
);

CREATE TABLE widget_templates (
    widget_id   UUID NOT NULL REFERENCES widget(id)   ON DELETE CASCADE,
    template_id UUID NOT NULL REFERENCES template(id) ON DELETE CASCADE,
    PRIMARY KEY (widget_id, template_id)
);

CREATE INDEX idx_device_group_group    ON device_group(group_id);
CREATE INDEX idx_widget_templates_tmpl ON widget_templates(template_id);