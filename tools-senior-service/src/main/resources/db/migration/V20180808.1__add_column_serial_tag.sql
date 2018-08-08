ALTER TABLE s_profiles ADD serial_tag char NULL COMMENT '流水标志';
ALTER TABLE s_profiles
  MODIFY COLUMN serial_tag char COMMENT '流水标志' AFTER host_ip;