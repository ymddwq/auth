DROP TABLE IF EXISTS user_auth_module;
CREATE TABLE user_auth_module(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    name VARCHAR(32)    COMMENT '模块名称' ,
    enabled VARCHAR(32)    COMMENT '是否可用' ,
    REVISION INT    COMMENT '乐观锁' ,
    CREATED_BY VARCHAR(32)    COMMENT '创建人' ,
    CREATED_TIME DATETIME    COMMENT '创建时间' ,
    UPDATED_BY VARCHAR(32)    COMMENT '更新人' ,
    UPDATED_TIME DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (id)
) COMMENT = '模块表 ';
ALTER TABLE user_auth_module COMMENT '模块表';

DROP TABLE IF EXISTS user_auth_menu;
CREATE TABLE user_auth_menu(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    enabled INT DEFAULT 0 COMMENT '是否可用 0可用1不可用' ,
    name VARCHAR(32) NOT NULL   COMMENT '菜单名称' ,
    pid INT    COMMENT '上级菜单id 自关联' ,
    REVISION INT    COMMENT '乐观锁' ,
    CREATED_BY VARCHAR(32)    COMMENT '创建人' ,
    CREATED_TIME DATETIME    COMMENT '创建时间' ,
    UPDATED_BY VARCHAR(32)    COMMENT '更新人' ,
    UPDATED_TIME DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (id)
) COMMENT = '菜单表 ';
ALTER TABLE user_auth_menu COMMENT '菜单表';

DROP TABLE IF EXISTS user_auth_permission;
CREATE TABLE user_auth_permission(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    enabled INT DEFAULT 0 COMMENT '是否可用 0可用1不可用' ,
    name VARCHAR(32) NOT NULL   COMMENT '权限名称' ,
    url VARCHAR(128) COMMENT '权限url' ,
    menuId INT COMMENT '所属菜单' ,
    REVISION INT    COMMENT '乐观锁' ,
    CREATED_BY VARCHAR(32) COMMENT '创建人' ,
    CREATED_TIME DATETIME COMMENT '创建时间' ,
    UPDATED_BY VARCHAR(32) COMMENT '更新人' ,
    UPDATED_TIME DATETIME COMMENT '更新时间' ,
    PRIMARY KEY (id)
) COMMENT = '权限表 ';
ALTER TABLE user_auth_permission COMMENT '权限表';

DROP TABLE IF EXISTS user_auth_role;
CREATE TABLE user_auth_role(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    name VARCHAR(32) NOT NULL   COMMENT '角色名' ,
    enabled INT   DEFAULT 0 COMMENT '是否可用 0可用1不可用' ,
    REVISION INT    COMMENT '乐观锁' ,
    CREATED_BY VARCHAR(32)    COMMENT '创建人' ,
    CREATED_TIME DATETIME    COMMENT '创建时间' ,
    UPDATED_BY VARCHAR(32)    COMMENT '更新人' ,
    UPDATED_TIME DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (id)
) COMMENT = '角色表 ';
ALTER TABLE user_auth_role COMMENT '角色表';

DROP TABLE IF EXISTS user_auth_role_permission;
CREATE TABLE user_auth_role_permission(
    roleId INT NOT NULL   COMMENT '角色id' ,
    permissionId INT NOT NULL   COMMENT '权限id' ,
    REVISION INT    COMMENT '乐观锁' ,
    CREATED_BY VARCHAR(32)    COMMENT '创建人' ,
    CREATED_TIME DATETIME    COMMENT '创建时间' ,
    UPDATED_BY VARCHAR(32)    COMMENT '更新人' ,
    UPDATED_TIME DATETIME    COMMENT '更新时间' 
) COMMENT = '角色权限表 ';
ALTER TABLE user_auth_role_permission COMMENT '角色权限表';



DROP TABLE IF EXISTS user_auth_sys_user;
CREATE TABLE user_auth_sys_user(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    name VARCHAR(32)    COMMENT '名称' ,
    password VARCHAR(32)    COMMENT '密码' ,
    enabled INT    COMMENT '是否可用' ,
    REVISION INT    COMMENT '乐观锁' ,
    CREATED_BY VARCHAR(32)    COMMENT '创建人' ,
    CREATED_TIME DATETIME    COMMENT '创建时间' ,
    UPDATED_BY VARCHAR(32)    COMMENT '更新人' ,
    UPDATED_TIME DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (id)
) COMMENT = '用户表 ';
ALTER TABLE user_auth_sys_user COMMENT '用户表';

DROP TABLE IF EXISTS user_auth_sys_user_role;
CREATE TABLE user_auth_sys_user_role(
    userId INT NOT NULL   COMMENT '用户id' ,
    roleId INT NOT NULL   COMMENT '角色id' ,
    REVISION INT    COMMENT '乐观锁' ,
    CREATED_BY VARCHAR(32)    COMMENT '创建人' ,
    CREATED_TIME DATETIME    COMMENT '创建时间' ,
    UPDATED_BY VARCHAR(32)    COMMENT '更新人' ,
    UPDATED_TIME DATETIME    COMMENT '更新时间' 
) COMMENT = '用户角色表 ';
ALTER TABLE user_auth_sys_user_role COMMENT '用户角色表';



ALTER TABLE user_auth_module ADD UNIQUE unique_module_name_index(name);
ALTER TABLE user_auth_menu ADD UNIQUE unique_menu_name_index(name);
ALTER TABLE user_auth_permission ADD UNIQUE unique_permission_name_index(name);
ALTER TABLE user_auth_role ADD UNIQUE unique_role_name_index(name);
ALTER TABLE user_auth_sys_user ADD UNIQUE unique_user_name_index(name);



