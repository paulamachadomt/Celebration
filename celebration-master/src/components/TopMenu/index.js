import React from 'react';
import { Link } from 'react-router-dom';
import logo from "../../assets/logo.svg";
import './styles.scss';

const TopMenu = () => {
  return (
    <div className="menu">
      <div className="menu-container">
        <div className="menu-logo">
          <Link to="/dashboard">
            <img className="menu-logo-image" src={logo} alt="Celebration Logo" />
          </Link>
        </div>
        <nav className="menu-nav">
          <Link className="menu-link" to="/about">Sobre</Link>
          <Link className="menu-link" to="/coming-soon" target="_blank">Em Breve</Link>
        </nav>
        <div className="menu-user">
          <Link className="menu-user-button button" to="/register">Cadastrar</Link>
        </div>
      </div>
    </div>
  )
}

export default TopMenu;