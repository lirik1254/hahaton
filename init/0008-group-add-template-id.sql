ALTER TABLE "group"
    ADD COLUMN template_id UUID REFERENCES template(id) ON DELETE SET NULL;
