-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 25, 2021 at 01:41 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `connect`
--

-- --------------------------------------------------------

--
-- Table structure for table `candidats_enternship`
--

CREATE TABLE `candidats_enternship` (
  `id` int(11) NOT NULL,
  `name` varchar(15) NOT NULL,
  `family_name` varchar(15) NOT NULL,
  `email` varchar(15) NOT NULL,
  `cv` varchar(6553) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `candidats_job`
--

CREATE TABLE `candidats_job` (
  `id` int(11) NOT NULL,
  `name` varchar(15) NOT NULL,
  `family_name` varchar(15) NOT NULL,
  `email` varchar(15) NOT NULL,
  `cv` varchar(6553) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(20) NOT NULL,
  `creation_date` date NOT NULL,
  `creation_user` int(11) NOT NULL,
  `status` varchar(4) NOT NULL DEFAULT 'EXPL',
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `category_name`, `creation_date`, `creation_user`, `status`, `description`) VALUES
(5, 'Affaire', '2021-07-15', 2352, 'EXPL', 'Affaire'),
(6, 'Chimie ', '2021-07-15', 2352, 'EXPL', 'Chimie '),
(7, 'Parachimie', '2021-07-15', 2352, 'EXPL', 'Parachimie'),
(8, 'IT', '2021-07-15', 2352, 'EXPL', 'Informatique / Télécoms'),
(9, 'Banque / Assurance', '2021-07-15', 2352, 'EXPL', 'Banque / Assurance'),
(11, 'Électronique', '2021-07-15', 2352, 'EXPL', 'Électronique / Électricité'),
(15, 'INFO', '2021-07-15', 1234566, 'ETUD', 'informatique'),
(16, 'Math', '2021-07-17', 1234566, 'ETUD', 'Mathematocs'),
(17, 'Category', '2021-07-17', 123, 'HEXP', 'category description'),
(18, 'Agroalimentaire', '2021-07-18', 2352, 'EXPL', 'Agroalimentaire'),
(19, 'cat123', '0000-00-00', 123, 'EXPL', 'hgkjkhioi');

-- --------------------------------------------------------

--
-- Table structure for table `club`
--

CREATE TABLE `club` (
  `id_club` int(11) NOT NULL,
  `institue` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `status` varchar(4) NOT NULL DEFAULT 'EXPL',
  `university` varchar(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `category` varchar(10) NOT NULL,
  `creation_date` date NOT NULL,
  `email` varchar(20) NOT NULL,
  `phone_number` int(11) DEFAULT NULL,
  `logo` varchar(255) NOT NULL,
  `creation_user` int(11) NOT NULL,
  `add_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `club`
--

INSERT INTO `club` (`id_club`, `institue`, `name`, `status`, `university`, `description`, `category`, `creation_date`, `email`, `phone_number`, `logo`, `creation_user`, `add_date`) VALUES
(33, 'institue1', 'name1', 'HEXP', 'university1', 'description 36 upd', 'category1', '2021-07-14', 'email1', 0, '', 0, '0000-00-00'),
(34, 'institue', 'name', 'ETUD', 'university', 'updated description 2', 'category', '2021-07-14', 'email', 0, '', 0, '0000-00-00'),
(35, 'khg', 'jhkd', 'EXPL', 'khgkj', 'jkh', 'categorytf', '2021-07-08', 'hjgj@hjgj.hj', 12345678, 'logo', 0, '0000-00-00'),
(36, 'Esprit', 'Eya', 'EXPL', 'lkhl', 'description 36 upd', 'IT', '2021-07-15', 'eya@esprit.com', 12345678, 'logo', 0, '0000-00-00'),
(37, 'Esprit', 'Eya', 'EXPL', 'university', 'helloo world', 'IT', '2021-07-15', 'eya@esprit.com', 12345678, 'logo', 0, '0000-00-00'),
(38, 'ESPRIT', 'lkhl', 'EXPL', 'eya', 'rg', 'Chimie ', '2021-07-16', 'hffd@jhhj.jkj', 12345678, 'logo', 0, '0000-00-00'),
(39, 'ytehk', 'hello', 'EXPL', 'frim', 'kjuli', 'IT', '2021-07-17', 'kljlit@jkjo.jhj', 12345678, '1200px-Grey_close_x.svg.png', 0, '0000-00-00'),
(40, '636++5', 'name', 'HEXP', 'university', 'description', 'category', '2021-07-17', 'email', 123, 'logo', 123, '2021-07-17'),
(41, 'institue4142', 'name4525', 'EXPL', 'university242', 'description', 'category', '2021-07-19', 'email', 4235463, 'logo', 24542, '2021-07-19');

-- --------------------------------------------------------

--
-- Table structure for table `club_history`
--

CREATE TABLE `club_history` (
  `id_history` int(11) NOT NULL,
  `event` varchar(20) NOT NULL,
  `entity_id` int(11) NOT NULL,
  `entity_type` varchar(20) NOT NULL,
  `update_date` date NOT NULL,
  `start_status` varchar(4) NOT NULL,
  `end_status` varchar(4) NOT NULL,
  `party1_id` int(11) NOT NULL,
  `party1_type` varchar(4) NOT NULL,
  `party2_id` int(11) NOT NULL,
  `party3_id` int(11) NOT NULL,
  `party2_type` varchar(4) NOT NULL,
  `party3_type` varchar(4) NOT NULL,
  `modification_user` int(11) NOT NULL,
  `original_history_id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `eventc`
--

CREATE TABLE `eventc` (
  `idevent` int(11) NOT NULL,
  `nomEvent` mediumtext NOT NULL,
  `decription` mediumtext NOT NULL,
  `lieu` mediumtext NOT NULL,
  `organisateur` mediumtext NOT NULL,
  `type` mediumtext NOT NULL,
  `invites` mediumtext NOT NULL,
  `domaine` mediumtext NOT NULL,
  `dateDebut` mediumtext NOT NULL,
  `dateFin` mediumtext NOT NULL,
  `sponsoring` tinyint(1) NOT NULL,
  `paiement` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `evente`
--

CREATE TABLE `evente` (
  `idevent` int(11) NOT NULL,
  `nomEvent` mediumtext NOT NULL,
  `description` mediumtext NOT NULL,
  `lieu` mediumtext NOT NULL,
  `organisateur` mediumtext NOT NULL,
  `type` mediumtext NOT NULL,
  `domaine` mediumtext NOT NULL,
  `dateDebut` mediumtext DEFAULT NULL,
  `dateFin` mediumtext DEFAULT NULL,
  `prtnrs` mediumtext DEFAULT NULL,
  `paiement` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `evente`
--

INSERT INTO `evente` (`idevent`, `nomEvent`, `description`, `lieu`, `organisateur`, `type`, `domaine`, `dateDebut`, `dateFin`, `prtnrs`, `paiement`) VALUES
(1, 'Test', 'offfff', 'Testlieu', 'Test', 'it', 'Testdomain', '', '', '', b'0'),
(3, 'x', 'a', 'c', 'y', 'd', 'e', '01/01/2001', '01/02/2001', NULL, b'1'),
(5, 'ConnectEvent', 'ggggggg', 'tunis', 'Chatti', '', 'IT', '1/1/2021', '1/1/2021', 'Noussayer', b'0'),
(6, 'TestTest', 'qsdjqsjkd', 'qsdqksdj', 'azeaze', 'qqq', 'IT', '01/01/2020', '02/01/2020', '', b'0'),
(7, 'qsdlqsdddd', 'qslldkqsljd', 'Tunis', 'qsldkqsldk', 'journée ouverte', 'IoT', '04/04/2002', '05/04/2002', 'ahmed', b'0'),
(8, '', '', '', '', '', '', '', '', '', b'0'),
(9, '', '', '', '', '', '', '', '', '', b'0'),
(10, 'jjjjj', 'jjjjj', 'jjjjj', 'jjjjj', 'jjjj', 'jjjjj', '', '', '', b'0'),
(11, 'jjjjj', 'jjjjj', 'jjjjj', 'jjjjj', 'jjjj', 'jjjjj', '', '', '', b'0'),
(12, 'jjjjj', 'kkkkk', 'kkk', 'kkkk', 'kkk', 'klkkk', '', '', 'kkkk', b'0'),
(13, 'llll', '', 'kkk', 'llll', 'kkkk', 'kkk', '', '', 'kkk', b'0'),
(15, 'llmmmm', 'mmmm', '', 'mmm', 'mmm', '', '', '', '', b'0'),
(16, '', '', '', '', '', '', '', '', '', b'0'),
(17, '', '', '', '', '', '', '', '', '', b'0'),
(18, '', '', '', '', '', '', '', '', '', b'0'),
(19, '', '', '', '', '', '', '', '', '', b'0'),
(20, '', '', '', '', '', '', '', '', '', b'0'),
(21, '', '', '', '', '', '', '', '', '', b'0'),
(22, '', '', '', '', '', '', '', '', '', b'0'),
(23, '', '', '', '', '', '', '', '', '', b'0'),
(24, '', '', '', '', '', '', '', '', '', b'0'),
(25, '', '', '', '', '', '', '', '', '', b'0'),
(26, '', '', '', '', '', '', '', '', '', b'0'),
(27, '', '', '', '', '', '', '', '', '', b'0'),
(28, 'hhh', '', 'hhheee', 'jbjk', 'ddd', 'ddd', '', '', 'ddd', b'0'),
(29, 'hhh', 'iiii', 'hhheee', 'jbjk', 'ddd', 'ddd', '10', '55', 'ddd', b'0'),
(30, 'hhhh', '', 'jjjj', 'jjjjj', 'kkkk', 'pppp', '', '', 'ppp', b'0'),
(31, 'uuuuuu', 'uuuuuuu', 'uuu', 'iuuuuu', '', '', '', '', '', b'0'),
(32, 'kkklll', '', 'kkk', 'kkk', '', '', 'lll', 'mmmm', '', b'0'),
(33, 'nouuu', 'jujj', 'jjj', 'hhh', 'jjj', '', '', '', '', b'0'),
(34, '', '', '', '', '', '', '', '', '', b'0'),
(35, 'hayyaa', 'gfvggg', '', '3aadhghghg', 'gggg', '', '', '', '', b'0'),
(36, '', '', '', '', '', '', '', '', '', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `internship`
--

CREATE TABLE `internship` (
  `id` int(11) NOT NULL,
  `ref` varchar(15) NOT NULL,
  `company` varchar(15) NOT NULL,
  `field` varchar(15) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `start_date` varchar(10) NOT NULL,
  `end_date` varchar(10) NOT NULL,
  `type` varchar(15) NOT NULL,
  `supervisor` varchar(15) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `invitation`
--

CREATE TABLE `invitation` (
  `invitation_id` int(11) NOT NULL,
  `status` varchar(4) NOT NULL,
  `creation_date` date NOT NULL,
  `club_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `acceptance_flag` varchar(1) NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invitation`
--

INSERT INTO `invitation` (`invitation_id`, `status`, `creation_date`, `club_id`, `member_id`, `post_id`, `acceptance_flag`) VALUES
(2, 'HEXP', '2021-07-19', 34, 123, 2, 'N');

-- --------------------------------------------------------

--
-- Table structure for table `invites`
--

CREATE TABLE `invites` (
  `idinvites` int(11) NOT NULL,
  `idevente` int(11) DEFAULT NULL,
  `ideventc` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invites`
--

INSERT INTO `invites` (`idinvites`, `idevente`, `ideventc`, `userid`) VALUES
(6, 8, NULL, 3),
(7, 9, NULL, 3),
(8, 16, NULL, 3),
(9, 35, NULL, 3),
(10, 35, NULL, 4),
(11, 17, NULL, 3),
(12, 18, NULL, 3),
(13, 19, NULL, 3),
(14, 20, NULL, 3),
(15, 21, NULL, 3),
(16, 22, NULL, 3),
(17, 23, NULL, 3),
(18, 24, NULL, 3),
(19, 25, NULL, 3),
(20, 26, NULL, 3),
(21, 27, NULL, 3),
(22, 34, NULL, 3),
(23, 8, NULL, 4),
(24, 9, NULL, 4),
(25, 16, NULL, 4),
(26, 17, NULL, 4),
(27, 18, NULL, 4),
(28, 8, NULL, 4),
(29, 9, NULL, 4),
(30, 16, NULL, 4),
(31, 17, NULL, 4),
(32, 18, NULL, 4),
(33, 19, NULL, 4),
(34, 20, NULL, 4),
(35, 21, NULL, 4),
(36, 22, NULL, 4),
(37, 23, NULL, 4),
(38, 24, NULL, 4),
(39, 25, NULL, 4),
(40, 26, NULL, 4),
(41, 27, NULL, 4),
(42, 34, NULL, 4),
(43, 36, NULL, 4),
(44, 19, NULL, 4),
(45, 20, NULL, 4),
(46, 21, NULL, 4),
(47, 22, NULL, 4),
(48, 23, NULL, 4),
(49, 24, NULL, 4),
(50, 25, NULL, 4),
(51, 26, NULL, 4),
(52, 27, NULL, 4),
(53, 34, NULL, 4);

-- --------------------------------------------------------

--
-- Table structure for table `membership`
--

CREATE TABLE `membership` (
  `post_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `club_id` int(11) NOT NULL,
  `status` varchar(4) NOT NULL,
  `membership_date` date NOT NULL,
  `manager_id` int(11) NOT NULL,
  `membership_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `membership`
--

INSERT INTO `membership` (`post_id`, `member_id`, `club_id`, `status`, `membership_date`, `manager_id`, `membership_id`) VALUES
(1, 123, 40, 'HEXP', '2021-07-19', 123, 1);

-- --------------------------------------------------------

--
-- Table structure for table `paiement`
--

CREATE TABLE `paiement` (
  `idp` int(11) NOT NULL,
  `email` mediumtext DEFAULT NULL,
  `tel` int(255) DEFAULT NULL,
  `carte` int(255) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  `idevente` int(11) DEFAULT NULL,
  `ideventc` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `paiement`
--

INSERT INTO `paiement` (`idp`, `email`, `tel`, `carte`, `iduser`, `idevente`, `ideventc`) VALUES
(2, 'noujoud.eljoudi@ensi-uma.tn', 777777, 777777, 3, 3, NULL),
(3, 'noujoud.eljoudi@ensi-uma.tn', 6666, 6666, 3, 3, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `participant`
--

CREATE TABLE `participant` (
  `idparticipant` int(11) NOT NULL,
  `Nom` varchar(200) NOT NULL,
  `Prenom` varchar(200) NOT NULL,
  `Age` int(11) NOT NULL,
  `email` varchar(2000) NOT NULL,
  `ecole` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `participant`
--

INSERT INTO `participant` (`idparticipant`, `Nom`, `Prenom`, `Age`, `email`, `ecole`) VALUES
(1, 'nouj', 'joudi', 25, 'hjghjjh@gmail.com', 'ensi'),
(7, 'UserTest', 'UserTest', 10, 'usertest@gmail.com', 'esprit'),
(8, 'UserTest', 'UserTest', 10, 'usertest@gmail.com', 'esprit'),
(9, 'MedAli', 'Chatti', 21, 'medali.chatti@xy', 'Esprit'),
(10, 'qsdqsdqsd', 'qsdqsdqd', 4, 'qsdqsdqs', 'qsdqsdqsd');

-- --------------------------------------------------------

--
-- Table structure for table `participantevente`
--

CREATE TABLE `participantevente` (
  `idparticipation` int(11) NOT NULL,
  `idevent` int(11) DEFAULT NULL,
  `idparticipant` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `participantevente`
--

INSERT INTO `participantevente` (`idparticipation`, `idevent`, `idparticipant`) VALUES
(1, 3, NULL),
(2, 3, NULL),
(3, 3, NULL),
(4, 6, NULL),
(5, 7, NULL),
(9, 6, 3),
(10, 5, 3),
(11, 3, 3),
(12, 3, 3),
(13, 3, 3),
(14, 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `personne`
--

CREATE TABLE `personne` (
  `id` int(15) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`) VALUES
(1, 'fhdgn', 'hjed'),
(2, 'jfgdjdy', 'hjhjkj'),
(3, 'ryu', 'dtij'),
(4, 'helloo', 'worls'),
(5, 'helloo', 'worls'),
(6, 'helloo2', 'worls'),
(7, 'helloo2', 'worls');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `post_id` int(11) NOT NULL,
  `post_name` varchar(20) NOT NULL,
  `creation_date` date NOT NULL,
  `creation_user` int(11) NOT NULL,
  `status` varchar(4) NOT NULL DEFAULT 'EXPL',
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`post_id`, `post_name`, `creation_date`, `creation_user`, `status`, `description`) VALUES
(1, 'postName', '2021-07-19', 123, 'EXPL', 'Hello world');

-- --------------------------------------------------------

--
-- Table structure for table `reclamations`
--

CREATE TABLE `reclamations` (
  `id` int(11) NOT NULL,
  `type` text NOT NULL,
  `nom` text NOT NULL,
  `prenom` text NOT NULL,
  `titre` text NOT NULL,
  `date_declaration` text NOT NULL,
  `description` text NOT NULL,
  `status` text NOT NULL,
  `answer` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reclamations`
--

INSERT INTO `reclamations` (`id`, `type`, `nom`, `prenom`, `titre`, `date_declaration`, `description`, `status`, `answer`) VALUES
(31, 'Technical Problem', 'ahmed', 'mohmed salah', 'probleme dans le commentaire', '2021-07-21', 'je peux pas commenter', 'in progress', 'réponse'),
(38, 'Recommendation', 'amor', 'jouini', 'recomm', '2021-07-21', 'ddwwqqq', 'done', 'réponseee'),
(46, 'opinion', 'haifa', 'jouini', 'probleme', '2021-07-28', 'test ', 'waiting', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `remboursement`
--

CREATE TABLE `remboursement` (
  `id` int(10) NOT NULL,
  `date` date DEFAULT NULL,
  `nom` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `club` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ville` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `telephone` int(20) NOT NULL,
  `demande` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `etatdemande` varchar(255) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `remboursement`
--

INSERT INTO `remboursement` (`id`, `date`, `nom`, `club`, `email`, `ville`, `telephone`, `demande`, `etatdemande`, `id_user`) VALUES
(6, '2021-07-12', 'iness', 'llllll', 'ines@gmail.com', 'tunis', 12345678, 'hello world1', 'Acceptée', 1),
(7, '2021-07-12', 'QSDFGH', '<sdwfghj', 'qsdfghj@gmail.com', '1223', 45745545, 'XSDWSFD', 'Refusée', 1),
(9, '2021-07-13', 'test', 'test', 'ines@gmail.com', 'test', 12345678, 'helloworldokoknnnn', 'En attente', 1),
(10, '2021-07-14', 'kkkk', 'fff', 'ddd@gmil.com', 'dddd', 55555555, 'jjjjjj', 'En attente', 1),
(11, '2021-07-14', 'aaa', 'aaa', 'aa@gmail.com', '1266', 11111111, 'zzzzz', 'En attente', 1),
(12, '2021-07-14', 'okok', 'okok', 'ok@gmail.com', 'ok', 12345678, 'ok', 'En attente', 1),
(13, '2021-07-16', 'iness', 'ok', 'iness.dkhily@esprit.tn', 'tunis', 12345678, 'hellokokokok', 'Acceptée', 1);

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `idreviews` int(11) NOT NULL,
  `note` int(11) DEFAULT NULL,
  `avis` varchar(255) DEFAULT NULL,
  `idevent` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`idreviews`, `note`, `avis`, `idevent`) VALUES
(3, 5, 'xyz', 5),
(4, 1, '', 6),
(5, 5, 'qsdqsjdqksjd	', 7);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(20) NOT NULL,
  `FirstName` varchar(30) DEFAULT NULL,
  `LastName` varchar(30) DEFAULT NULL,
  `Club` varchar(30) DEFAULT NULL,
  `Email` varchar(30) DEFAULT NULL,
  `Password` varchar(30) DEFAULT NULL,
  `DateBirth` date DEFAULT NULL,
  `Gender` mediumtext DEFAULT NULL,
  `Role` mediumtext DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `FirstName`, `LastName`, `Club`, `Email`, `Password`, `DateBirth`, `Gender`, `Role`) VALUES
(35, 'Wissal', 'YERMANI', NULL, NULL, NULL, NULL, NULL, ''),
(85, 'dd', 'gf', 'x', 'hjggf', '111', '2021-07-08', 'gender', NULL),
(6, 'Eya', 'Eya', NULL, NULL, NULL, NULL, NULL, ''),
(7, 'Ines', 'Ines', NULL, NULL, NULL, NULL, NULL, ''),
(8, 'Haifa', 'Haifa', NULL, NULL, NULL, NULL, NULL, ''),
(9, 'Noussayer', 'Noussayer', NULL, NULL, NULL, NULL, NULL, ''),
(10, 'MedAli', 'MedAli', NULL, NULL, NULL, NULL, NULL, ''),
(13, 'Ines', 'DKHILI', NULL, NULL, NULL, NULL, NULL, ''),
(14, 'Haifa', 'JOUINI', NULL, NULL, NULL, NULL, NULL, ''),
(15, 'Noussayer', 'DERBEL', NULL, NULL, NULL, NULL, NULL, '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `idauth` int(11) NOT NULL,
  `login` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` varchar(200) NOT NULL,
  `Nom` varchar(2000) DEFAULT NULL,
  `Prenom` varchar(2000) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `email` varchar(2000) DEFAULT NULL,
  `ecole` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`idauth`, `login`, `password`, `role`, `Nom`, `Prenom`, `Age`, `email`, `ecole`) VALUES
(1, 'adminE', 'adminE', 'AdminE', NULL, NULL, NULL, NULL, NULL),
(2, 'adminC', 'adminC', 'AdminC', NULL, NULL, NULL, NULL, NULL),
(3, 'user', 'user', 'participant', 'chatti', 'chatti', 55, 'noujoud.eljoudi@ensi-uma.tn', 'esprit'),
(4, 'chatti', 'chatti123', 'participant', NULL, NULL, NULL, 'neljoudi@3s.com.tn', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `work`
--

CREATE TABLE `work` (
  `id` int(11) NOT NULL,
  `company` varchar(15) NOT NULL,
  `field` varchar(15) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `start_date` int(10) NOT NULL,
  `agreementType` varchar(10) NOT NULL,
  `position` varchar(10) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `candidats_enternship`
--
ALTER TABLE `candidats_enternship`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `candidats_job`
--
ALTER TABLE `candidats_job`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`),
  ADD UNIQUE KEY `category_name_2` (`category_name`),
  ADD KEY `category_name` (`category_name`),
  ADD KEY `category_name_3` (`category_name`);

--
-- Indexes for table `club`
--
ALTER TABLE `club`
  ADD PRIMARY KEY (`id_club`),
  ADD UNIQUE KEY `institue` (`institue`,`name`,`university`),
  ADD KEY `institue_2` (`institue`,`name`,`university`);

--
-- Indexes for table `club_history`
--
ALTER TABLE `club_history`
  ADD PRIMARY KEY (`id_history`);

--
-- Indexes for table `eventc`
--
ALTER TABLE `eventc`
  ADD PRIMARY KEY (`idevent`);

--
-- Indexes for table `evente`
--
ALTER TABLE `evente`
  ADD PRIMARY KEY (`idevent`);

--
-- Indexes for table `internship`
--
ALTER TABLE `internship`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `invitation`
--
ALTER TABLE `invitation`
  ADD PRIMARY KEY (`invitation_id`),
  ADD UNIQUE KEY `status_2` (`status`,`club_id`,`member_id`),
  ADD KEY `status` (`status`,`club_id`,`member_id`);

--
-- Indexes for table `invites`
--
ALTER TABLE `invites`
  ADD PRIMARY KEY (`idinvites`),
  ADD KEY `idevente` (`idevente`),
  ADD KEY `ideventc` (`ideventc`),
  ADD KEY `userid` (`userid`);

--
-- Indexes for table `membership`
--
ALTER TABLE `membership`
  ADD PRIMARY KEY (`membership_id`);

--
-- Indexes for table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`idp`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idevente` (`idevente`),
  ADD KEY `ideventc` (`ideventc`);

--
-- Indexes for table `participant`
--
ALTER TABLE `participant`
  ADD PRIMARY KEY (`idparticipant`);

--
-- Indexes for table `participantevente`
--
ALTER TABLE `participantevente`
  ADD PRIMARY KEY (`idparticipation`),
  ADD KEY `idevent` (`idevent`),
  ADD KEY `idparticipant` (`idparticipant`);

--
-- Indexes for table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`post_id`),
  ADD UNIQUE KEY `post_name` (`post_name`),
  ADD UNIQUE KEY `post_name_3` (`post_name`),
  ADD KEY `post_name_2` (`post_name`);

--
-- Indexes for table `reclamations`
--
ALTER TABLE `reclamations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `remboursement`
--
ALTER TABLE `remboursement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`idreviews`),
  ADD KEY `idevent` (`idevent`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `login_user` (`Email`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`idauth`);

--
-- Indexes for table `work`
--
ALTER TABLE `work`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `club`
--
ALTER TABLE `club`
  MODIFY `id_club` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `club_history`
--
ALTER TABLE `club_history`
  MODIFY `id_history` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `eventc`
--
ALTER TABLE `eventc`
  MODIFY `idevent` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `evente`
--
ALTER TABLE `evente`
  MODIFY `idevent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `invitation`
--
ALTER TABLE `invitation`
  MODIFY `invitation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `invites`
--
ALTER TABLE `invites`
  MODIFY `idinvites` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `membership`
--
ALTER TABLE `membership`
  MODIFY `membership_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `paiement`
--
ALTER TABLE `paiement`
  MODIFY `idp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `participant`
--
ALTER TABLE `participant`
  MODIFY `idparticipant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `participantevente`
--
ALTER TABLE `participantevente`
  MODIFY `idparticipation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `reclamations`
--
ALTER TABLE `reclamations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `remboursement`
--
ALTER TABLE `remboursement`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `idreviews` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `idauth` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `invites`
--
ALTER TABLE `invites`
  ADD CONSTRAINT `invites_ibfk_1` FOREIGN KEY (`idevente`) REFERENCES `evente` (`idevent`),
  ADD CONSTRAINT `invites_ibfk_2` FOREIGN KEY (`ideventc`) REFERENCES `eventc` (`idevent`),
  ADD CONSTRAINT `invites_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `users` (`idauth`);

--
-- Constraints for table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `paiement_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `users` (`idauth`),
  ADD CONSTRAINT `paiement_ibfk_2` FOREIGN KEY (`idevente`) REFERENCES `evente` (`idevent`),
  ADD CONSTRAINT `paiement_ibfk_3` FOREIGN KEY (`ideventc`) REFERENCES `eventc` (`idevent`);

--
-- Constraints for table `participantevente`
--
ALTER TABLE `participantevente`
  ADD CONSTRAINT `participantevente_ibfk_1` FOREIGN KEY (`idevent`) REFERENCES `evente` (`idevent`),
  ADD CONSTRAINT `participantevente_ibfk_2` FOREIGN KEY (`idparticipant`) REFERENCES `users` (`idauth`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`idevent`) REFERENCES `evente` (`idevent`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
