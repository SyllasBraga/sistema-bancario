/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      backgroundImage: {
        'botao-editar': "url('/public/editar-texto.png')",
        'botao-excluir': "url('/public/excluir.png')",
      },
    },
  },
  plugins: [],
}

