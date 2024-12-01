const Botao = ({ label, onClick, type = "button" }) => (
    <button
      type={type}
      onClick={onClick}
      className="bg-blue-500 ml-2 text-white px-1 py-1 rounded hover:bg-blue-600"
    >
      {label}
    </button>
  );
  
  export default Botao;