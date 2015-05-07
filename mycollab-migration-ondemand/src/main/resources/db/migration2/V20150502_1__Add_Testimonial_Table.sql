UPDATE m_tracker_bug SET status='Reopened' WHERE status='Reopenned';
UPDATE m_prj_milestone SET status='InProgress' WHERE status='In Progress';
CREATE TABLE `s_testimonial` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `displayName` varchar(100) NOT NULL,
  `jobRole` varchar(100) NOT NULL,
  `company` varchar(100) DEFAULT NULL,
  `testimonial` text NOT NULL,
  `website` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

