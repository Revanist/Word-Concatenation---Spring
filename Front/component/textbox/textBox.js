import React, { useState } from 'react';
import axios from 'axios';
import './TextBox.css';

function TextBox() {
  const [text, setText] = useState('');

  const handleChange = (event) => {
    setText(event.target.value);
  }

  const handleClick = async () => {
    const data = { text: text };
    const response = await axios.post('http://localhost:8080/api/saveData', data);
    console.log(response.data);
  }

  return (
    <div className="textBox">
      <input type="text" value={text} onChange={handleChange} />
      <button onClick={handleClick}>Aktar</button>
    </div>
  );
}

export default TextBox;
