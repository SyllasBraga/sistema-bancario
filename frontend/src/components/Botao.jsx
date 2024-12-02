const Botao = ({ label, onClick, type = "button" }) => (
    <button
      type={type}
      onClick={onClick}
      className="w-20 h-8 bg-blue-500 text-sm text-white py-1 px-1 rounded hover:bg-blue-600"
    >
      {label}
    </button>
  );
  
  export default Botao;