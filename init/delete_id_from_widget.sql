ALTER TABLE widget_templates DROP CONSTRAINT IF EXISTS widget_templates_widget_id_fkey;

ALTER TABLE widget DROP COLUMN IF EXISTS id;
ALTER TABLE widget ADD PRIMARY KEY (name);

ALTER TABLE widget_templates DROP CONSTRAINT IF EXISTS widget_templates_pkey;
ALTER TABLE widget_templates DROP COLUMN IF EXISTS widget_id;
ALTER TABLE widget_templates ADD COLUMN widget_name varchar(255) NOT NULL
    REFERENCES widget(name) ON DELETE CASCADE;
ALTER TABLE widget_templates ADD PRIMARY KEY (widget_name, template_id);
