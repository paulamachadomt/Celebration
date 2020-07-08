import React, { Fragment } from 'react';
import './styles.scss';

const TabMenu = ({ rightTab, leftTab, rightTitle, leftTitle, activeTab, setActiveTab }) => {

  return (
    <Fragment>            
      <div className="tab-menu">
        <ul className="tab-menu-list">
          <li 
            onClick={() => setActiveTab(leftTab)}
            className={activeTab === leftTab ? 'tab-menu-list-item is-active' : 'tab-menu-list-item'}
          >
            <span>{leftTitle}</span>
          </li>
          <li 
            onClick={() => setActiveTab(rightTab)}
            className={activeTab === rightTab ? 'tab-menu-list-item is-active' : 'tab-menu-list-item'}
          >
            <span>{rightTitle}</span>
          </li>
        </ul>
      </div>


    </Fragment>
  )
}

export default TabMenu;