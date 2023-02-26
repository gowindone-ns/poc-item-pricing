import { useState } from "react";
import "./product-price-editor.css";
import { Button } from "../core/Button";

function ProductPriceEditor({
  productPrice = {
    id:"",
    countryStoreId: "INTN01",
    sku: "xSKU",
    productName: "xProductName",
    price: 0,
  },
  onSave = () => void 0
}) {
  // TODO extract common component to avoid markup duplication
  const [storeId, setStoreId] = useState(productPrice.countryStoreId);
  const [sku, setSku] = useState(productPrice.sku);
  const [productName, setProductName] = useState(productPrice.productName);
  const [price, setPrice] = useState(productPrice.price);

  function handleChange(e) {
    const { name, value } = e.target;
    switch (name) {
      case "countryStoreId":
        setStoreId(value);
        break;
      case "sku":
        setSku(value);
        break;
      case "productName":
        setProductName(value);
        break;
      case "price":
        setPrice(value);
        break;
      default:
        console.warn("unhandled input");
    }
  }<input onClick={onSave} name="submit" type="submit"></input>
  return (
    <div className="product-price-editor">
      <form onSubmit={onSave} name="productPriceEditor">
        <input name="id" type="hidden" value={productPrice.id}></input>
        <div className="field-row">
          <label className="field-col">Store ID</label>
          <input
            className="field-col"
            name="countryStoreId"
            type="text"
            onChange={handleChange}
            value={storeId}
          ></input>
        </div>
        <div className="field-row">
          <label className="field-col">SKU</label>
          <input
            className="field-col"
            name="sku"
            type="text"
            onChange={handleChange}
            value={sku}
          ></input>
        </div>
        <div className="field-row">
          <label className="field-col">Product Name</label>
          <input
            className="field-col"
            name="productName"
            type="text"
            onChange={handleChange}
            value={productName}
          ></input>
        </div>
        <div className="field-row">
          <label className="field-col">Price</label>
          <input
            className="field-col"
            name="price"
            type="text"
            onChange={handleChange}
            value={price}
          ></input>
        </div> 
        <Button
          onClick={(e) => {
            // document.forms["productPriceEditor"].submit();
            e.preventDefault();
            onSave();
            
          }}
          caption="Save"
        />       
      </form>
    </div>
  );
}

export { ProductPriceEditor };
