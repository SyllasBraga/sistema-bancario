import React from "react";

const Paginacao = ({ currentPage, totalPages, onPageChange }) => {
  const handlePageChange = (page) => {
    if (page >= 0 && page < totalPages) {
      onPageChange(page);
    }
  };

  const renderPageNumbers = () => {
    const pages = [];
    for (let i = 0; i < totalPages; i++) {
      pages.push(
        <button
          key={i}
          onClick={() => handlePageChange(i)}
          className={`px-3 py-1 mx-1 border rounded ${
            currentPage === i
              ? "bg-blue-500 text-white"
              : "bg-white text-blue-500 border-blue-500"
          }`}
        >
          {i + 1}
        </button>
      );
    }
    return pages;
  };

  return (
    <div className="flex items-center justify-center mt-4">
      <button
        onClick={() => handlePageChange(currentPage - 1)}
        disabled={currentPage === 0}
        className="px-3 py-1 mx-1 border rounded bg-white text-blue-500 border-blue-500 disabled:opacity-50"
      >
        Anterior
      </button>
      {renderPageNumbers()}
      <button
        onClick={() => handlePageChange(currentPage + 1)}
        disabled={currentPage === totalPages - 1}
        className="px-3 py-1 mx-1 border rounded bg-white text-blue-500 border-blue-500 disabled:opacity-50"
      >
        Próximo
      </button>
    </div>
  );
};

export default Paginacao;