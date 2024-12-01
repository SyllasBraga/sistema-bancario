import React from "react";
import Modal from "react-modal";

const ModalConfirmacao = ({ isOpen, onDeletePessoa, onRequestClose, mensagem }) => (
  <Modal
    isOpen={isOpen}
    onRequestClose={onRequestClose}
    className="bg-white rounded-lg shadow-lg p-6 w-1/5"
    overlayClassName="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center"
  >
    <h2 className="text-lg font-bold text-blue-600 mb-4">Confirmação de exclusão</h2>
    <p className="text-center mb-6">{mensagem}</p>
    <div className="flex justify-center gap-4">
      <button
        onClick={onDeletePessoa}
        className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-gray-500"
      >
        Confirmar
      </button>
      <button
        onClick={onRequestClose}
        className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-gray-500"
      >
        Cancelar
      </button>
    </div>
  </Modal>
);

export default ModalConfirmacao;
