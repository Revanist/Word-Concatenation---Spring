import axios from "axios";
import { useEffect, useState } from "react";

function App() {
  const [inputValues, setInputValues] = useState([""]);
  const [result, setResult] = useState("");
  const [processTime, setprocessTime] = useState("");

  const handleChange = (index, event) => {
    const newInputValues = [...inputValues];
    newInputValues[index] = event.target.value;
    setInputValues(newInputValues);
  };

  const handleAddInput = () => {
    setInputValues([...inputValues, ""]);
  };

  const handleRemoveInput = (index) => {
    const newInputValues = [...inputValues];
    newInputValues.splice(index, 1);
    setInputValues(newInputValues);
  };

  function handleClick() {
    const text = inputValues.join("-");
    axios.post("http://localhost:8080/api/text", { text }).then((response) => {
      console.log(response);
    });
    alert(text);
  }

  function handleResultData() {
    axios.get("http://localhost:8080/api/text", { result, processTime }).then((response) => {
      setResult(response.data.result);
      setprocessTime(response.processTime);
      console.log(" " + response.data.result);
      console.log(" " + response.data.processTime);
    });
  }

  return (
    <div>
      <div className="title">{"Text Combiner"}</div>
      <div className="left-title">{"String Input"}</div>
      {inputValues.map((inputValue, index) => (
        <div key={index}>
          {"Text "  + (index + 1) + "    "}
          <input
            type="text"
            value={inputValue}
            onChange={(event) => handleChange(index, event)}
          />
          {inputValues.length !== 1 && (
            <button type="button" onClick={() => handleRemoveInput(index)}>
              Remove
            </button>
          )}
        </div>
      ))}
      <button type="button" onClick={handleAddInput}>
        Add New Text
      </button>
      <button className="combine-button" onClick={handleClick}>Combine</button>
      <p></p>
      <div className="left-title">{"Input"}</div>
      <div className="text-box">
        {inputValues.length > 0 && (
          <p type="text-box">{" " + inputValues.join("-")}</p>
        )}
      </div>
      <div className="left-title">{"Result Text"}</div>
      <button className="result-button" type="button" onClick={() => handleResultData()}>
        Result
      </button>
      <div className="text-box">{<p type="text-box">{result}</p>}</div>
      <div  className="text-box">{<p type="text-box">{processTime}</p>}</div>
    </div>
  );
}

export default App;
