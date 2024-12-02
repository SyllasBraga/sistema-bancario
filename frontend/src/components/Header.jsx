import React from "react";
import { Link, useLocation } from "react-router-dom";

const Header = () => {
  const location = useLocation();

  const menuItems = [
    { name: "Pessoas", path: "/pessoa-page", subMenu: "Gerenciar Pessoas" },
    { name: "Contas", path: "/conta-page", subMenu: "Gerenciar Contas" },
    { name: "Movimentações", path: "/movimentacao-page", subMenu: "Gerenciar Movimentações" },
  ];

  return (
    <header className="bg-gray-800 text-white p-4 shadow-md">
      <div className="container mx-auto flex justify-between items-center">
        <h1 className="text-xl font-semibold">Sistema de Banco</h1>
        <nav className="flex space-x-12">
          {menuItems.map((item) => (
            <div key={item.name} className="relative group">
              {location.pathname === item.path ? (
                <button
                  className="text-gray-400 text-sm cursor-not-allowed"
                  disabled
                >
                  {item.name}
                </button>
              ) : (
                <button className="hover:text-gray-300 text-sm">
                  {item.name}
                </button>
              )}
              {location.pathname !== item.path && (
                <div className="absolute left-0 top-full bg-white text-black shadow-md rounded-lg opacity-0 group-hover:opacity-100 transition-opacity flex justify-center">
                  <Link
                    to={item.path}
                    className="block px-4 py-2 text-sm hover:bg-gray-200 whitespace-nowrap"
                  >
                    {item.subMenu}
                  </Link>
                </div>
              )}
            </div>
          ))}
        </nav>
      </div>
    </header>
  );
};

export default Header;