-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  jeu. 14 fév. 2019 à 16:23
-- Version du serveur :  10.1.30-MariaDB
-- Version de PHP :  7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `db_gab`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateurs`
--

CREATE TABLE `administrateurs` (
  `numero_administrateur` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `passe` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `administrateurs`
--

INSERT INTO `administrateurs` (`numero_administrateur`, `nom`, `passe`) VALUES
(1, 'test', 'test');

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `numero_client` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `sexe` varchar(255) NOT NULL,
  `adresse` varchar(500) NOT NULL,
  `telephone` int(20) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `passe` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`numero_client`, `nom`, `prenom`, `sexe`, `adresse`, `telephone`, `mail`, `passe`) VALUES
(5, 'OURO AGORO', 'fadil', 'Masculin', 'Lomé-Togo', 2552522, 'fadou@gmail.com', 'e1z87'),
(7, 'OURO AGORO', 'fadoul', 'Masculin', 'Agoé-nyivé', 2585565, 'fad@gmail.com', 'sqShj'),
(8, 'OURO AGORO', 'fadoul', 'Masculin', 'Agoé-nyivé', 2585565, 'fad@gmail.com', 'sqShj'),
(10, 'OURO AGORO', 'fadila', 'Feminin', 'Agoé', 90252668, 'fadila@mzil.com', 'Zz8Dx'),
(12, 'Sodoli', 'Manavi', 'Masculin', 'Djagblé', 92585662, 'sodo@mail.com', 'qODal'),
(14, 'Kodjo', 'Kofi', 'Masculin', 'Lomé-Togo', 32025025, 'k@mail.com', '1234');

-- --------------------------------------------------------

--
-- Structure de la table `comptes`
--

CREATE TABLE `comptes` (
  `id_compte` int(11) NOT NULL,
  `numero_compte` varchar(255) NOT NULL,
  `solde` double DEFAULT NULL,
  `numero_client` int(11) NOT NULL,
  `numero_administrateur` int(11) NOT NULL,
  `date_creation` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `comptes`
--

INSERT INTO `comptes` (`id_compte`, `numero_compte`, `solde`, `numero_client`, `numero_administrateur`, `date_creation`) VALUES
(8, '60006332149676', 340000, 12, 1, '2019-02-06'),
(11, '60001106872643', 50000, 8, 1, '2019-02-11'),
(12, '60007901173774', 450000, 14, 1, '2019-02-14');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrateurs`
--
ALTER TABLE `administrateurs`
  ADD PRIMARY KEY (`numero_administrateur`);

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`numero_client`);

--
-- Index pour la table `comptes`
--
ALTER TABLE `comptes`
  ADD PRIMARY KEY (`id_compte`),
  ADD KEY `numero_client` (`numero_client`),
  ADD KEY `numero_administrateur` (`numero_administrateur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `administrateurs`
--
ALTER TABLE `administrateurs`
  MODIFY `numero_administrateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `numero_client` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `comptes`
--
ALTER TABLE `comptes`
  MODIFY `id_compte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
