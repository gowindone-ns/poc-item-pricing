import "./dialog.css";


function Dialog({
  title = ({ className }) => {
    return <span className={className}>{"Edit Product Pricing"}</span>;
  },
  content = ({ className }) => {
    return <div className={className}>{"Content"}</div>;
  },
}) {
  const Title = title;
  const Content = content;
  return (
    <div className="modal">
      <div className="dialog">
        <Title className="title" />
        {content}
        {/* <Content className="content" /> */}
        
      </div>
    </div>
  );
}

export { Dialog };
