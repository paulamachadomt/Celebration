import React from 'react';
import './styles.scss';

const HomeCard = (props) => {
  const { cardImage, cardTitle, cardText } = props;
  return (
    <div className="card">
      <img src={cardImage} alt={cardTitle} />
      <div className="card-text">
        <span>{cardTitle}</span>
        <p>{cardText}</p>
      </div>
    </div>
  )
}

export default HomeCard;