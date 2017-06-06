-- ----------------------------
-- View structure for `v_admin`
-- ----------------------------
DROP VIEW IF EXISTS `v_admin`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_admin` AS select `a`.`id` AS `id`,`a`.`username` AS `username`,`a`.`password` AS `password`,`a`.`time` AS `time`,date_format(from_unixtime(`a`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time` from `admin` `a` ;

-- ----------------------------
-- View structure for `v_garousel`
-- ----------------------------
DROP VIEW IF EXISTS `v_garousel`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_garousel` AS select `g`.`id` AS `id`,`g`.`title` AS `title`,`g`.`url` AS `url`,`g`.`catalog_id` AS `catalog_id`,`g`.`hyperlink` AS `hyperlink`,`g`.`time` AS `time`,(select `gc`.`garousel_catalog` from `garousel_catalog` `gc` where (`gc`.`id` = `g`.`catalog_id`)) AS `catalog`,concat('http://39.108.82.55:8080/TendaEHome/',`g`.`url`) AS `gerousel_url`,date_format(from_unixtime(`g`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time` from `garousel` `g` ;

-- ----------------------------
-- View structure for `v_goods_activity`
-- ----------------------------
DROP VIEW IF EXISTS `v_goods_activity`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_goods_activity` AS select `ga`.`id` AS `id`,`ga`.`goods_id` AS `goods_id`,`ga`.`start_time` AS `start_time`,`ga`.`end_time` AS `end_time`,`ga`.`discount` AS `discount`,date_format(from_unixtime(`ga`.`start_time`),'%Y-%m-%d %H:%i:%S') AS `start_date`,date_format(from_unixtime(`ga`.`end_time`),'%Y-%m-%d %H:%i:%S') AS `end_date`,cast((`ga`.`discount` * `g`.`price`) as decimal(11,2)) AS `dis_price` from (`goods_activity` `ga` join `goods` `g`) where ((unix_timestamp(now()) between `ga`.`start_time` and (`ga`.`end_time` + 86400)) and (`g`.`id` = `ga`.`goods_id`) and ((`ga`.`end_time` + 86400) <> unix_timestamp(now()))) ;

-- ----------------------------
-- View structure for `v_goods_count`
-- ----------------------------
DROP VIEW IF EXISTS `v_goods_count`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_goods_count` AS select `od`.`goods_id` AS `goods_id`,sum(`od`.`num`) AS `count` from (`orders_detail` `od` join `orders` `o`) where ((`o`.`state` > 1) and (`o`.`id` = `od`.`order_id`)) group by `od`.`goods_id` ;


-- ----------------------------
-- View structure for `v_orders_details`
-- ----------------------------
DROP VIEW IF EXISTS `v_orders_details`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_orders_details` AS select `od`.`id` AS `id`,`od`.`order_id` AS `order_id`,(select `o`.`order_num` from `orders` `o` where (`o`.`id` = `od`.`order_id`)) AS `order_num`,`od`.`goods_id` AS `goods_id`,(select `g`.`name` from `goods` `g` where (`od`.`goods_id` = `g`.`id`)) AS `goods_name`,(select concat('http://39.108.82.55:8080/TendaEHome/',`g`.`url`) from `goods` `g` where (`od`.`goods_id` = `g`.`id`)) AS `goods_url`,`od`.`num` AS `num`,cast((select (`od`.`num` * `g`.`price`) from `goods` `g` where (`od`.`goods_id` = `g`.`id`)) as decimal(11,2)) AS `prices` from `orders_detail` `od` ;

-- ----------------------------
-- View structure for `v_order_total`
-- ----------------------------
DROP VIEW IF EXISTS `v_order_total`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_order_total` AS select `od`.`order_id` AS `order_id`,cast(sum((`g`.`price` * `od`.`num`)) as decimal(11,2)) AS `total` from (`goods` `g` join `orders_detail` `od`) where (`g`.`id` = `od`.`goods_id`) group by `od`.`order_id` ;

-- ----------------------------
-- View structure for `v_tv`
-- ----------------------------
DROP VIEW IF EXISTS `v_tv`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_tv` AS select `t`.`id` AS `id`,`t`.`name` AS `name`,`t`.`img_url` AS `img_url`,`t`.`tv_url` AS `tv_url`,`t`.`time` AS `time`,date_format(from_unixtime(`t`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time` from `tv` `t` ;


-- ----------------------------
-- View structure for `v_user_address`
-- ----------------------------
DROP VIEW IF EXISTS `v_user_address`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user_address` AS select `u`.`id` AS `userid`,`ua`.`id` AS `id`,concat(`ua`.`receiver`,' ',`ua`.`sex`,' ',`ua`.`tel`,' ',`ua`.`address`) AS `address` from (`user_address` `ua` join `user` `u`) where ((`u`.`id` = `ua`.`userid`) and (`ua`.`default_` = 1)) ;

-- ----------------------------
-- View structure for `v_user_feedback`
-- ----------------------------
DROP VIEW IF EXISTS `v_user_feedback`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user_feedback` AS select `uf`.`id` AS `id`,`uf`.`message` AS `message`,`uf`.`user_id` AS `user_id`,`uf`.`time` AS `time`,`uf`.`read` AS `read`,date_format(from_unixtime(`uf`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,`u`.`phone` AS `phone` from (`user_feedback` `uf` left join `user` `u` on((`u`.`id` = `uf`.`user_id`))) ;

-- ----------------------------
-- View structure for `v_user`
-- ----------------------------
DROP VIEW IF EXISTS `v_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user` AS select `u`.`id` AS `id`,`u`.`phone` AS `phone`,`u`.`password` AS `password`,`u`.`time` AS `time`,`u`.`nickname` AS `nickname`,`u`.`head_url` AS `head_url`,date_format(from_unixtime(`u`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,concat('http://39.108.82.55:8080/TendaEHome/',`u`.`head_url`) AS `url`,coalesce(`ua`.`address`,'') AS `address`,coalesce(`ua`.`id`,0) AS `address_id` from (`user` `u` left join `v_user_address` `ua` on((`ua`.`userid` = `u`.`id`))) ;

-- ----------------------------
-- View structure for `v_orders`
-- ----------------------------
DROP VIEW IF EXISTS `v_orders`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_orders` AS select `o`.`id` AS `id`,`o`.`userid` AS `userid`,`o`.`time` AS `time`,(select `vot`.`total` from `v_order_total` `vot` where (`vot`.`order_id` = `o`.`id`)) AS `total`,date_format(from_unixtime(`o`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,`o`.`state` AS `state`,(case `o`.`state` when 0 then '取消订单' when 1 then '未付款' when 2 then '已付款，未发货' when 3 then '已发货，未签收' else '已签收，订单完成' end) AS `status`,(select concat(`ua`.`receiver`,' ',`ua`.`sex`,' ',`ua`.`tel`,' ',`ua`.`address`) from `user_address` `ua` where (`ua`.`id` = `o`.`address_id`)) AS `address`,`o`.`address_id` AS `address_id`,`o`.`order_num` AS `order_num` from `orders` `o` ;

-- ----------------------------
-- View structure for `v_goods`
-- ----------------------------
DROP VIEW IF EXISTS `v_goods`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_goods` AS select `g`.`id` AS `goods_id`,`g`.`name` AS `name`,`g`.`price` AS `price`,`g`.`url` AS `url`,`g`.`catalog_id` AS `catalog_id`,`g`.`description` AS `description`,`g`.`time` AS `time`,`g`.`state` AS `state`,date_format(from_unixtime(`g`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,(select `gc`.`catalog` from `goods_catalog` `gc` where (`g`.`catalog_id` = `gc`.`id`)) AS `catalog`,coalesce(`vgc`.`count`,0) AS `count`,coalesce(`ga`.`dis_price`,0) AS `dis_price`,coalesce(`ga`.`start_date`,'') AS `start_date`,coalesce(`ga`.`end_date`,'') AS `end_date`,concat('http://39.108.82.55:8080/TendaEHome/',`g`.`url`) AS `goods_url`,`g`.`origin` AS `origin` from ((`goods` `g` left join `v_goods_count` `vgc` on((`vgc`.`goods_id` = `g`.`id`))) left join `v_goods_activity` `ga` on((`g`.`id` = `ga`.`goods_id`))) ;