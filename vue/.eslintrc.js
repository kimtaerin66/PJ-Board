module.exports = {
  root: true,
  env: {
    node: true,
  },
  extends: ['plugin:vue/essential', '@vue/prettier', 'eslint:recommended'],
  rules: {
    // 'vue/multi-word-component-names': [
    //   'error',
    //   {
    //     ignores: ['default'],
    //   },
    // ],
    'no-console': 'off',
    // "no-console": process.env.NODE_ENV === "production" ? "error" : "off",
    // "no-debugger": process.env.NODE_ENV === "production" ? "error" : "off"
    'vue/html-self-closing': 'off', //<input></input>
    'vue/no-parsing-error': [
      2,
      {
        //<input></input>
        'x-invalid-end-tag': false,
      },
    ],
    'no-unexpected-multiline': 'error',
    'no-unused-vars': 'off',
    'prettier/prettier': [
      'error',
      {
        singleQuote: true,
        semi: true,
        useTabs: false,
        tabWidth: 2,
        trailingComma: 'all',
        printWidth: 80,
        bracketSpacing: true,
        arrowParens: 'avoid',
        endOfLine: 'auto', //async 사용시 에러 않나게
      },
    ],
  },
  parserOptions: {
    parser: 'babel-eslint',
  },
  overrides: [
    {
      files: [
        '**/__tests__/*.{j,t}s?(x)',
        '**/tests/unit/**/*.spec.{j,t}s?(x)',
      ],
      env: {
        jest: true,
      },
    },
  ],
  globals: {
    Snap: false,
  },
};
