CREATE DATABASE `gestion_de_stock`;
CREATE TABLE `utilisateur` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `mot_de_pass` varchar(45) DEFAULT NULL,
  `admin` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID`)
);
CREATE TABLE `produit` (
  `code_produit` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `categorie` varchar(45) DEFAULT NULL,
  `prix` double DEFAULT NULL,
  PRIMARY KEY (`code_produit`)
);
CREATE TABLE `inventaire` (
  `inventaireID` int NOT NULL AUTO_INCREMENT,
  `code_produit` int NOT NULL,
  `quantite` int DEFAULT NULL,
  `remarques` varchar(45) DEFAULT NULL,
  `IDTransaction` int DEFAULT NULL,
  `expirationDate` date DEFAULT NULL,
  PRIMARY KEY (`inventaireID`,`code_produit`),
  KEY `cp_idx` (`code_produit`),
  CONSTRAINT `cp` FOREIGN KEY (`code_produit`) REFERENCES `produit` (`code_produit`)
);
