-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 29 mai 2020 à 11:59
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
SET @@global.time_zone = '+00:00';

SET @@session.time_zone = '+00:00';
START TRANSACTION;



/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetplanning`
--

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(40) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`ID`, `Nom`) VALUES
(1, 'Mathematiques'),
(2, 'Electronique'),
(3, 'Physique'),
(4, 'Probabilités');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
CREATE TABLE IF NOT EXISTS `enseignant` (
  `ID_utilisateurs` int(11) NOT NULL,
  `Id_cours` varchar(100) NOT NULL,
  KEY `ID_utilisateurs` (`ID_utilisateurs`),
  KEY `Id_cours` (`Id_cours`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`ID_utilisateurs`, `Id_cours`) VALUES
(3, '1'),
(16, '1'),
(17, '2'),
(17, '2'),
(18, '3'),
(19, '3'),
(20, '4'),
(21, '4'),
(22, '5'),
(23, '5');

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `Id_utilisateurs` int(11) NOT NULL,
  `Numero` varchar(100) NOT NULL,
  `Id_groupe` int(11) NOT NULL,
  PRIMARY KEY (`Id_utilisateurs`),
  KEY `Id_groupe` (`Id_groupe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`Id_utilisateurs`, `Numero`, `Id_groupe`) VALUES
(4, '1234', 1),
(5, '12345', 1),
(6, '123456', 2),
(7, '123457', 2),
(8, '12345678', 3),
(9, '123456789', 3),
(10, '1234567891', 4),
(11, '12345678910', 4),
(12, '123456789101', 5),
(13, '1234567891012', 5),
(14, '12345678910123', 6),
(15, '123456789101234', 6);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE IF NOT EXISTS `groupe` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(100) NOT NULL,
  `ID_promotion` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_promotion` (`ID_promotion`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`ID`, `Nom`, `ID_promotion`) VALUES
(1, 'Gr1', 1),
(2, 'Gr2', 1),
(3, 'Gr1', 2),
(4, 'Gr2', 2),
(5, 'Gr1', 3),
(6, 'Gr2', 3);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(40) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`ID`, `Nom`) VALUES
(1, 'Ing1'),
(2, 'Ing2'),
(3, 'Ing3');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(100) NOT NULL,
  `Capacite` int(4) NOT NULL,
  `ID_site` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_site` (`ID_site`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`ID`, `Nom`, `Capacite`, `ID_site`) VALUES
(1, 'P330', 55, 1),
(2, 'P445', 30, 1),
(3, 'E110', 200, 2),
(4, 'E20', 35, 2),
(5, 'D230', 60, 3),
(6, 'D356', 20, 3);

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

DROP TABLE IF EXISTS `seance`;
CREATE TABLE IF NOT EXISTS `seance` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Semaine` int(3) NOT NULL,
  `Debut` datetime NOT NULL,
  `Fin` datetime NOT NULL,
  `Etat` enum('En cours de validation','validée','annulée') NOT NULL,
  `Id_cours` int(11) NOT NULL,
  `Id_Typ` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Id_cours` (`Id_cours`),
  KEY `Id_Typ` (`Id_Typ`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`ID`, `Semaine`, `Debut`, `Fin`, `Etat`, `Id_cours`, `Id_Typ`) VALUES
(1, 17, '2020-04-23 08:30:00', '2020-04-23 10:00:00', 'validée', 1, 1),
(2, 17, '2020-04-23 10:30:00', '2020-04-23 12:00:00', 'En cours de validation', 2, 2),
(3, 17, '2020-04-23 13:30:00', '2020-04-23 15:00:00', 'annulée', 3, 3),
(4, 17, '2020-04-23 15:15:00', '2020-04-23 16:45:00', 'validée', 3, 4),
(5, 17, '2020-04-23 17:00:00', '2020-04-23 20:30:00', 'validée', 4, 5);

-- --------------------------------------------------------

--
-- Structure de la table `seance_enseignant`
--

DROP TABLE IF EXISTS `seance_enseignant`;
CREATE TABLE IF NOT EXISTS `seance_enseignant` (
  `id_seance` int(11) NOT NULL,
  `id_enseignant` int(11) NOT NULL,
  KEY `id_seance` (`id_seance`),
  KEY `id_enseignant` (`id_enseignant`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seance_enseignant`
--

INSERT INTO `seance_enseignant` (`id_seance`, `id_enseignant`) VALUES
(1, 3),
(2, 20),
(3, 17),
(4, 16),
(5, 18),
(1, 16);

-- --------------------------------------------------------

--
-- Structure de la table `seance_groupe`
--

DROP TABLE IF EXISTS `seance_groupe`;
CREATE TABLE IF NOT EXISTS `seance_groupe` (
  `id_seance` int(11) NOT NULL,
  `id_groupe` int(11) NOT NULL,
  KEY `id_seance` (`id_seance`),
  KEY `id_groupe` (`id_groupe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seance_groupe`
--

INSERT INTO `seance_groupe` (`id_seance`, `id_groupe`) VALUES
(1, 1),
(2, 3),
(3, 5),
(4, 4),
(5, 1);

-- --------------------------------------------------------

--
-- Structure de la table `seance_salle`
--

DROP TABLE IF EXISTS `seance_salle`;
CREATE TABLE IF NOT EXISTS `seance_salle` (
  `id_seance` int(11) NOT NULL,
  `id_salle` varchar(11) NOT NULL,
  KEY `id_seance` (`id_seance`),
  KEY `id_salle` (`id_salle`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seance_salle`
--

INSERT INTO `seance_salle` (`id_seance`, `id_salle`) VALUES
(1, '1'),
(2, '3'),
(3, '1'),
(4, '2'),
(5, '5'),
(2, '6'),
(3, '5.3');

-- --------------------------------------------------------

--
-- Structure de la table `site`
--

DROP TABLE IF EXISTS `site`;
CREATE TABLE IF NOT EXISTS `site` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(40) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`ID`, `Nom`) VALUES
(1, 'E1'),
(2, 'E2'),
(3, 'E3');

-- --------------------------------------------------------

--
-- Structure de la table `type_cours`
--

DROP TABLE IF EXISTS `type_cours`;
CREATE TABLE IF NOT EXISTS `type_cours` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(40) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `type_cours`
--

INSERT INTO `type_cours` (`ID`, `Nom`) VALUES
(1, 'Cours interactif'),
(2, 'Cours magistral'),
(3, 'TD'),
(4, 'TP'),
(5, 'Soutient');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(250) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Nom` varchar(40) NOT NULL,
  `Prenom` varchar(40) NOT NULL,
  `Droit` enum('Administrateur','Référent pédagogique','Enseignant','Etudiant') NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`ID`, `Email`, `Password`, `Nom`, `Prenom`, `Droit`) VALUES
(1, 'david@edu.ece.fr', 'david123', 'Vert', 'David', 'Administrateur'),
(2, 'Jean@edu.ece.fr', 'Jean123', 'Rouge', 'Jean', 'Référent pédagogique'),
(3, 'Pierre@edu.ece.fr', 'Pierre123', 'Bleu', 'Papier', 'Enseignant'),
(4, 'Papier@edu.ece.fr', 'Papier123', 'Jaune', 'Ciseau', 'Etudiant'),
(5, 'Thibaut@edu.ece.fr', 'Thibaut123', 'Guigui', 'Thibaut', 'Etudiant'),
(6, 'Parpaing@edu.ece.fr', 'Parpaing123', 'Parpaing', 'Avril', 'Etudiant'),
(7, 'Ete@edu.ece.fr', 'Ete123', 'Ete', 'Lea', 'Etudiant'),
(8, 'Emma@edu.ece.fr', 'Emma123', 'Terre', 'Emma', 'Etudiant'),
(9, 'Leaedu.ece.fr', 'Lea123', 'Violet', 'Lea', 'Etudiant'),
(10, 'Audi@edu.ece.fr', 'Audi123', 'Audi', 'Gaston', 'Etudiant'),
(11, 'Raoult@edu.ece.fr', 'Raoult123', 'Raoult', 'Herve', 'Etudiant'),
(12, 'Jackson@edu.ece.fr', 'Jackson123', 'Jackson', 'Michael', 'Etudiant'),
(13, '2pac@edu.ece.fr', '2pac123', 'Shakur', 'Pac', 'Etudiant'),
(14, 'Niska@edu.ece.fr', 'Niska123', 'Niska', 'Stan', 'Etudiant'),
(15, 'Thomas@edu.ece.fr', 'Thomas123', 'Porte', 'Thomas', 'Etudiant'),
(16, 'Raymond@edu.ece.fr', 'Raymond123', 'Pinar', 'Raymond', 'Enseignant'),
(17, 'Coudray@edu.ece.fr', 'Coudray123', 'Coudray', 'Fabienne', 'Enseignant'),
(18, 'Segado@edu.ece.fr', 'Segado123', 'Segado', 'JP', 'Enseignant'),
(19, 'Mokbher@edu.ece.fr', 'Mokbher123', 'Mokbher', 'Arash', 'Enseignant'),
(20, 'Nani@edu.ece.fr', 'Nani123', 'Nani', 'Igor', 'Enseignant'),
(21, 'CR7@edu.ece.fr', 'Ronaldo123', 'Cristiano', 'Ronaldo', 'Enseignant'),
(22, 'Messi@edu.ece.fr', 'Messi123', 'Messi', 'Lionel', 'Enseignant'),
(23, 'Menon@edu.ece.fr', 'Menon123', 'Menon', 'Tristan', 'Enseignant'),
(24, 'Moulaga@edu.ece.fr', 'Moulaga123', 'Moulaga', 'Chritophe', 'Enseignant');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
