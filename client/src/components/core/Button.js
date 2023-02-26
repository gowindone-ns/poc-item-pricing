function Button({ className = "", caption = "Btn", onClick = () => void 0 }) {
  return (
    <button className={className} onClick={onClick}>
      {caption}
    </button>
  );
}

export { Button };
