-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 28/03/2025 às 23:52
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `hotelaria`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `clientes`
--

CREATE TABLE `clientes` (
  `NOME` varchar(100) NOT NULL,
  `CPF` varchar(11) NOT NULL,
  `TELEFONE` varchar(15) DEFAULT NULL,
  `DATA_NASC` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `hoteis`
--

CREATE TABLE `hoteis` (
  `CNPJ` varchar(14) NOT NULL,
  `NOME` varchar(40) NOT NULL,
  `ENDERECO` varchar(100) DEFAULT NULL,
  `UF` varchar(2) DEFAULT NULL,
  `TELEFONE` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `quartos`
--

CREATE TABLE `quartos` (
  `ID_CLIENTES` varchar(11) DEFAULT NULL,
  `ID_HOTEL` varchar(14) DEFAULT NULL,
  `RESERVADO` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`CPF`);

--
-- Índices de tabela `hoteis`
--
ALTER TABLE `hoteis`
  ADD PRIMARY KEY (`CNPJ`);

--
-- Índices de tabela `quartos`
--
ALTER TABLE `quartos`
  ADD KEY `ID_CLIENTES` (`ID_CLIENTES`),
  ADD KEY `ID_HOTEL` (`ID_HOTEL`);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `quartos`
--
ALTER TABLE `quartos`
  ADD CONSTRAINT `quartos_ibfk_1` FOREIGN KEY (`ID_CLIENTES`) REFERENCES `clientes` (`CPF`),
  ADD CONSTRAINT `quartos_ibfk_2` FOREIGN KEY (`ID_HOTEL`) REFERENCES `hoteis` (`CNPJ`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
