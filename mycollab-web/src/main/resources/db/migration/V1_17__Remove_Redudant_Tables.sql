ALTER TABLE `s_report_bug_issue` CHANGE COLUMN `ipaddress` `ipaddress` VARCHAR(40) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL  ;
drop table `m_todo`;
drop table `m_todo_category`;
drop table `m_todo_status`;
drop table `m_tag_taxonomy`;
drop table `m_tag_tag`;
drop table `m_group`;
drop table `m_friend_friend`;