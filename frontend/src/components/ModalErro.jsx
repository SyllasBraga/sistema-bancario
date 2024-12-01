import React from "react";
import Modal from "react-modal";

const ModalErro = ({ isOpen, onRequestClose, mensagem }) => (
  <Modal
    isOpen={isOpen}
    onRequestClose={onRequestClose}
    className="bg-white rounded-lg shadow-lg p-6 w-1/3 mx-auto mt-20"
    overlayClassName="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center"
  >
    <h2 className="text-lg font-bold text-red-600 mb-4">Erro</h2>
    <p>{mensagem}</p>
    <button
      onClick={onRequestClose}
      className="mt-4 bg-red-500 text-white py-2 px-4 rounded"
    >
      Fechar
    </button>
  </Modal>
);

export default ModalErro;