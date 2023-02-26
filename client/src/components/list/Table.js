import "./table.css";
import { Button } from "../core/Button";

function Table({
  onRowClick = (rowData) => {
    return void 0;
  },
  header = [
    {
      name: "countryStoreId",
      displayName: "Store ID",
      view: ({ value }) => <div className="col align-left">{value}</div>,
    },
    {
      name: "sku",
      displayName: "SKU",
      view: ({ value }) => <div className="col align-left">{value}</div>,
    },
    {
      name: "productName",
      displayName: "Product Name",
      view: ({ value }) => <div className="col align-left">{value}</div>,
    },
    {
      name: "price",
      displayName: "Price",
      view: ({ value }) => <div className="col align-right">{value}</div>,
    },
    {
      name: "lastFeedDate",
      displayName: "Updated Date",
      view: ({ value }) => {
        let dateValue;
        if (value === "Updated Date") {
          dateValue = 0;
        } else {
          dateValue = new Date(value);
          dateValue = dateValue.toLocaleDateString();
        }
        return <div className="col align-right">{dateValue || value}</div>;
      },
    },
    {
      name: "actions",
      displayName: "Actions",
      view: ({ value }) => {
        const handleClick = ()=>{
          onRowClick(value);
        }
        return (
          <div className="col align-right">
            <Button onClick={handleClick} caption="Edit" />
          </div>
        );
      },
    },
  ],
  data = [{ productName: "Samsung Galaxy Tab A", price: 12000 }],
}) {
  const headerRow = {
    countryStoreId: "Store ID",
    sku: "SKU",
    productName: "Product Name",
    price: "Price",
    lastFeedDate: "Updated Date",
    actions: "Actions",
  };
  return (
    <div className="table">
      <div className="header-row">
        <Row record={headerRow} metaData={header}></Row>
      </div>
      {data.map((row) => {
        return <Row record={row} metaData={header}></Row>;
      })}
    </div>
  );
}

function Row({
  record = { itemName: "Samsung Galaxy Tab A", itemPrice: 12000 },
  metaData = [
    {
      name: "itemName",
      displayName: "Item Name",
      view: ({ value }) => <span>{value}</span>,
    },
    {
      name: "itemPrice",
      displayName: "Item Price",
      view: ({ value }) => <span>{value}</span>,
    },
  ],
}) {
  return (
    <div className="row">
      {metaData.map((meta) => {
        const View = meta.view;
        let value = record[meta.name];
        if (meta.name === "actions") {
          value = record;
        }

        return <View value={value} />;
      })}
    </div>
  );
}

export { Table };
