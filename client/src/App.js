import "./App.css";
import { Button, Table, Dialog, ProductPriceEditor } from "./components";
import { useEffect, useRef } from "react";
import { useState } from "react";

function App() {
  const [itemFeeds, setItemFeeds] = useState([]);
  const [itemFeed, setItemFeed] = useState(undefined);
  // TODO locale specific user facing string from a messagebundle in client side artifact
  const [countryCode, setCountryCode] = useState("IN");
  const [file, setFile] = useState();
  const fileElement = useRef();
  const formElement = useRef();
  const [rangeFilter, setRangeFilter] = useState(false);

  useEffect(() => {
    if (rangeFilter) {
      const query = {
        query: {
          selectExpression: { columns: ["cspi"] },
          fromExpression: { entity: "CountryStoreProduct cspi" },
          whereExpression: {
            range: { from: "1000", to: "2000", field: "price" },
          },
        },
      };
      fetch("http://localhost:8080/products/search", {
        method: "post",
        body: JSON.stringify(query),
        headers: { "Content-Type": "application/json" },
      }).then((resp) =>
        resp.json().then((data) => {
          setItemFeeds(data);
        })
      );
    }

    fetch(`http://localhost:8080/products?countryCode=${countryCode}`).then(
      (resp) =>
        resp.json().then((data) => {
          setItemFeeds(data);
        })
    );
    // setItemFeeds([{ itemName: "Samsung Galaxy Tab A", itemPrice: 12012 }]);
  }, [itemFeed, countryCode,rangeFilter]);

  function handleProductPriceSave(e) {
    // e.preventDefault();
    const formData = document.forms["productPriceEditor"];

    const data = new FormData(formData);

    const toServer = {
      id: data.get("id"),
      countryStoreId: data.get("countryStoreId"),
      sku: data.get("sku"),
      productName: data.get("productName"),
      price: data.get("price"),
    };

    fetch("http://localhost:8080/products", {
      method: "post",
      body: JSON.stringify(toServer),
      headers: { "Content-Type": "application/json" },
    }).then((resp) => {
      console.log("form submitted");
      setItemFeed(undefined);
    });
  }
  function handleChange(event) {
    handleSubmit();
  }

  function handleSubmit(event) {
    const url = `http://localhost:8080/products/${countryCode}/import`;
    const formData = new FormData();
    formData.append("file", fileElement.current.files[0]);
    formData.append("fileName", fileElement.current.files[0].name);

    fetch(url, {
      method: "post",
      body: formData,
    }).then((resp) => console.log("submitted"));
  }
  const upload = (
    <div className="upload">
      <form ref={formElement} onSubmit={handleSubmit}>
        <input
          ref={fileElement}
          type="file"
          onChange={handleChange}
          className="hide"
        />
      </form>
    </div>
  );

  return (
    <div className="App">
      <div className="top-actions">
        <input
          className="country-code"
          value={countryCode}
          type="text"
          onChange={(t) => {
            setCountryCode(t.target.value);
          }}
        />
        {upload}
        <Button
          onClick={() => {
            fileElement.current.click();
          }}
          className="import-feed"
          caption="Load"
        />
        <div>
          <label>Price from 1000 to 5000</label>
          <input
            className="filter"
            value={rangeFilter}
            type="checkbox"
            onChange={(t) => {
              setRangeFilter(t.target.checked);
            }}
          />
        </div>
      </div>
      <Table onRowClick={(feed) => setItemFeed(feed)} data={itemFeeds} />
      {!!itemFeed && (
        <Dialog
          content={
            <ProductPriceEditor
              productPrice={itemFeed}
              onSave={handleProductPriceSave}
            />
          }
        />
      )}
    </div>
  );
}

export default App;
