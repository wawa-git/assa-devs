-- -----------------------------------------------------
-- Table `SW_SETID`
-- -----------------------------------------------------
ALTER TABLE `SW_SETID` ADD CONSTRAINT `PK_SETID` PRIMARY KEY (`SETID_ID`);

-- -----------------------------------------------------
-- Table `SW_BU_EX`
-- -----------------------------------------------------
ALTER  TABLE `SW_BU_EX`  ADD CONSTRAINT `PK_BU_EX` PRIMARY KEY (`BU_EX_ID`);

-- -----------------------------------------------------
-- Table `SW_DEPT`
-- -----------------------------------------------------
ALTER  TABLE  `SW_DEPT` ADD CONSTRAINT `PK_DEPARTMENT` PRIMARY KEY (`DEPT_ID`);

ALTER TABLE `SW_DEPT` ADD CONSTRAINT `FK_DEFAULT_BU_EXON_DEPT` FOREIGN KEY (`DEFAULT_BU_EX_ID` )
    REFERENCES `SW_BU_EX` (`BU_EX_ID` );

-- -----------------------------------------------------
-- Table `SW_CURRENCY_CD`
-- -----------------------------------------------------
ALTER  TABLE `SW_CURRENCY_CD` ADD CONSTRAINT `PK_CURRENCY` PRIMARY KEY (`CURRENCY_ID`) ;

-- -----------------------------------------------------
-- Table `SW_OPRID`
-- -----------------------------------------------------
ALTER  TABLE `SW_OPRID` ADD CONSTRAINT `PK_OPRID` PRIMARY KEY (`OPRID_ID`);

-- -----------------------------------------------------
-- Table `SW_EMPLOYEES`
-- -----------------------------------------------------
ALTER  TABLE `SW_EMPLOYEES` ADD CONSTRAINT `PK_EMPLOYEES` PRIMARY KEY (`EMPL_ID`);

ALTER  TABLE `SW_EMPLOYEES`  ADD CONSTRAINT `FK_DEPARTMENT_ON_EMPLOYEES` FOREIGN KEY (`DEPT_ID` )
    REFERENCES `SW_DEPT` (`DEPT_ID` );

ALTER  TABLE `SW_EMPLOYEES`  ADD CONSTRAINT `FK_BU_EX_ON_EMPLOYEES` FOREIGN KEY (`BU_EX_ID` )
    REFERENCES `SW_BU_EX` (`BU_EX_ID` );

ALTER  TABLE `SW_EMPLOYEES`  ADD CONSTRAINT `FK_MANAGER_ON_EMPLOYEES` FOREIGN KEY (`MANAGER_ID` )
    REFERENCES `SW_EMPLOYEES` (`EMPL_ID` );

ALTER  TABLE `SW_EMPLOYEES`  ADD CONSTRAINT `FK_OPRID_ON_EMPLOYEES` FOREIGN KEY (`OPRID_ID` )
    REFERENCES `SW_OPRID` (`OPRID_ID` );

-- -----------------------------------------------------
-- Table `SW_BU_PC`
-- -----------------------------------------------------
ALTER TABLE `SW_BU_PC` ADD CONSTRAINT `PK_BU_PC` PRIMARY KEY (`BU_PC_ID`);

-- -----------------------------------------------------
-- Table `SW_PROJECT`
-- -----------------------------------------------------
ALTER TABLE `SW_PROJECT` ADD CONSTRAINT `PK_PROJECT`  PRIMARY KEY (`PROJECT_ID`);

ALTER TABLE `SW_PROJECT` ADD CONSTRAINT `FK_BU_PC_ON_PROJECT` FOREIGN KEY (`BU_PC_ID` )
    REFERENCES `SW_BU_PC` (`BU_PC_ID` );

ALTER TABLE `SW_PROJECT`  ADD CONSTRAINT `FK_MANAGER_ON_PROJECT` FOREIGN KEY (`MANAGER_ID` )
    REFERENCES `SW_EMPLOYEES` (`EMPL_ID` );

-- -----------------------------------------------------
-- Table `SW_TASK`
-- -----------------------------------------------------
ALTER  TABLE `SW_TASK` ADD CONSTRAINT `PK_TASK` PRIMARY KEY (`TASK_ID`);

ALTER  TABLE `SW_TASK` ADD CONSTRAINT `FK_PROJECT_ON_TASK` FOREIGN KEY (`PROJECT_ID` )
    REFERENCES `SW_PROJECT` (`PROJECT_ID` );

ALTER  TABLE `SW_TASK`  ADD CONSTRAINT `FK_EMPLOYEE_ON_TASK` FOREIGN KEY (`EMPL_ID` )
    REFERENCES `SW_EMPLOYEES` (`EMPL_ID` );

-- -----------------------------------------------------
-- Table `SW_TEAM`
-- -----------------------------------------------------
ALTER  TABLE `SW_TEAM` ADD CONSTRAINT `PK_TEAM` PRIMARY KEY (`TEAM_ID`);

ALTER  TABLE `SW_TEAM` ADD CONSTRAINT `FK_MANAGER_ON_TEAM` FOREIGN KEY (`MANAGER_ID` )
    REFERENCES `SW_EMPLOYEES` (`EMPL_ID` );

-- -----------------------------------------------------
-- Table `SW_TIME`
-- -----------------------------------------------------
ALTER  TABLE `SW_TIME` ADD CONSTRAINT `PK_TIME` PRIMARY KEY (`TIME_ID`);

ALTER  TABLE `SW_TIME`  ADD CONSTRAINT `FK_EMPLOYEE_ON_TIME` FOREIGN KEY (`EMPL_ID` )
    REFERENCES `SW_EMPLOYEES` (`EMPL_ID` );

ALTER  TABLE `SW_TIME`  ADD CONSTRAINT `FK_TASK_ON_TIME` FOREIGN KEY (`TASK_ID` )
    REFERENCES `SW_TASK` (`TASK_ID` );

ALTER  TABLE `SW_TIME`  ADD CONSTRAINT `FK_TEAM_ON_TIME` FOREIGN KEY (`TEAM_ID` )
    REFERENCES `SW_TEAM` (`TEAM_ID` );

-- -----------------------------------------------------
-- Table `SW_BU`
-- -----------------------------------------------------
ALTER  TABLE `SW_BU`  ADD CONSTRAINT `PK_BU` PRIMARY KEY (`BU_ID`);

ALTER  TABLE `SW_BU`  ADD CONSTRAINT `FK_BU_EX_ON_BU` FOREIGN KEY (`BU_EX_ID` )
    REFERENCES `SW_BU_EX` (`BU_EX_ID` );

ALTER  TABLE `SW_BU`  ADD CONSTRAINT `FK_BU_PC_ON_BU` FOREIGN KEY (`BU_PC_ID` )
    REFERENCES `SW_BU_PC` (`BU_PC_ID` );

-- -----------------------------------------------------
-- Table `SW_BU_DEPT`
-- -----------------------------------------------------
ALTER  TABLE `SW_BU_DEPT` ADD CONSTRAINT `PK_BU_DEPT` PRIMARY KEY (`BU_ID`, `DEPT_ID`);

ALTER  TABLE `SW_BU_DEPT` ADD CONSTRAINT `FK_BU_ON_BU_DEPT` FOREIGN KEY (`BU_ID` )
    REFERENCES `SW_BU` (`BU_ID` );
    
ALTER  TABLE `SW_BU_DEPT` ADD CONSTRAINT `FK_DEPARTMENT_ON_BU_DEPT`FOREIGN KEY (`DEPT_ID` )
    REFERENCES `SW_DEPT` (`DEPT_ID` );

-- -----------------------------------------------------
-- Table `SW_SETID_BU`
-- -----------------------------------------------------
ALTER  TABLE `SW_SETID_BU` ADD CONSTRAINT `PK_SETID_BU` PRIMARY KEY (`SETID_ID`, `BU_ID`);

ALTER  TABLE `SW_SETID_BU` ADD CONSTRAINT `FK_SETID_ON_SETID_BU` FOREIGN KEY (`SETID_ID` )
    REFERENCES `SW_SETID` (`SETID_ID` );

ALTER  TABLE `SW_SETID_BU` ADD CONSTRAINT `FK_BU_ONSETID_BU` FOREIGN KEY (`BU_ID` )
    REFERENCES `SW_BU` (`BU_ID` );

-- -----------------------------------------------------
-- Table `SW_TEAM_EMPLOYEES`
-- -----------------------------------------------------
ALTER  TABLE `SW_TEAM_EMPLOYEES` ADD CONSTRAINT `PK_TEAM_EMPLOYEES` PRIMARY KEY (`TEAM_ID`, `EMPL_ID`) ,

ALTER  TABLE `SW_TEAM_EMPLOYEES` ADD CONSTRAINT `FK_TEAM_ON_TEAM_EMPLOYEES` ADD  FOREIGN KEY (`TEAM_ID` )
    REFERENCES `SW_TEAM` (`TEAM_ID` );
    
ALTER  TABLE `SW_TEAM_EMPLOYEES` ADD CONSTRAINT `FK_EMPLOYEE_ON_TEAM_EMPLOYEES` FOREIGN KEY (`EMPL_ID` )
    REFERENCES `SW_EMPLOYEES` (`EMPL_ID` );

-- -----------------------------------------------------
-- Table `SW_ROLE`
-- -----------------------------------------------------
ALTER  TABLE `SW_ROLE` ADD CONSTRAINT `PK_ROLE` PRIMARY KEY (`ROLE_ID`);

-- -----------------------------------------------------
-- Table `SW_USER_ROLE`
-- -----------------------------------------------------
ALTER  TABLE  `SW_USER_ROLE` ADD CONSTRAINT `PK_USER_ROLE` PRIMARY KEY (`USER_ROLE_ID`);

ALTER  TABLE  `SW_USER_ROLE` ADD CONSTRAINT `FK_ROLE_ON_USER_ROLE` FOREIGN KEY (`ROLE_ID` )
    REFERENCES `SW_ROLE` (`ROLE_ID` );
    
ALTER  TABLE  `SW_USER_ROLE` ADD CONSTRAINT `FK_EMPLOYEE_ON_USER_ROLE` FOREIGN KEY (`EMPL_ID` )
    REFERENCES `SW_EMPLOYEES` (`EMPL_ID` );
    
ALTER  TABLE  `SW_USER_ROLE` ADD CONSTRAINT `FK_BU_ON_USER_ROLE` FOREIGN KEY (`BU_ID` )
    REFERENCES `SW_BU` (`BU_ID` );