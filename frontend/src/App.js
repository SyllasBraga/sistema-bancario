import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PessoaPage from "./pages/PessoaPage";
import ContaPage from "./pages/ContaPage";
import MovimentacaoPage from "./pages/MovimentacaoPage";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/pessoa-page" element={<PessoaPage />} />
        <Route path="/conta-page" element={<ContaPage />} />
        <Route path="/movimentacao-page" element={<MovimentacaoPage />} />
      </Routes>
    </Router>
  );
};

export default App;