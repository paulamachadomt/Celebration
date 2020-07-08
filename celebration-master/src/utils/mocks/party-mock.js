const partyMock = [
  {
    id: '1',
    date: '10/01/2020',
    host: {
      id: '04',
      name: 'Queiroz'
    },
    text: 'Não perca essa maravilhosa festaaa',
    title: 'Festa da Laranja',
    location: 'Rua Felipe Andrade, 203',
    guests: [
      {
        id: '01',
        name: 'Flávio',
        confirmation: true
      },
      {
        id: '02',
        name: 'Carlos',
        confirmation: true
      },
      {
        id: '03',
        name: 'Eduardo',
        confirmation: false
      }
    ],
    contribuition: [
      {
        item: 'Laranja',
        guest: '01'
      },
      {
        item: 'Refrigerante',
        guest: '02'
      },
      {
        item: 'Bolo',
        guest: '03'
      },
    ]
  },
  {
    id: '2',
    date: '10/01/2020',
    host: {
      id: '01',
      name: 'Flávio'
    },
    text: 'Não perca essa maravilhosa festaaa',
    title: 'Festival de Chocolate',
    location: 'Rua Felipe Andrade, 203',
    guests: [
      {
        id: '04',
        name: 'Queiroz',
        confirmation: true
      },
      {
        id: '02',
        name: 'Carlos',
        confirmation: true
      },
      {
        id: '03',
        name: 'Eduardo',
        confirmation: true
      }
    ],
    contribuition: [
      {
        item: 'Laranja',
        guest: '04'
      },
      {
        item: 'Refrigerante',
        guest: '02'
      },
      {
        item: 'Bolo',
        guest: '03'
      },
    ]
  },
  {
    id: '3',
    date: '10/01/2020',
    host: {
      id: '05',
      name: 'Jair'
    },
    text: '54 dias já',
    title: 'Maratona Ministério Freestyle',
    location: 'Rua Felipe Andrade, 203',
    guests: [
      {
        id: '01',
        name: 'Flávio'
      },
      {
        id: '02',
        name: 'Carlos'
      },
      {
        id: '03',
        name: 'Eduardo'
      }
    ],
    contribuition: [
      {
        item: 'Laranja',
        guest: '01'
      },
      {
        item: 'Refrigerante',
        guest: '02'
      },
      {
        item: 'Bolo',
        guest: '03'
      },
    ]
  },
  {
    id: '4',
    date: '10/01/2020',
    host: {
      id: '05',
      name: 'Jair'
    },
    text: 'Festa no cemitério',
    title: 'Bacanal do "E daí?"',
    location: 'Cemitério Jardim da Paz',
    guests: [
      {
        id: '01',
        name: 'Flávio'
      },
      {
        id: '03',
        name: 'Eduardo'
      },
      {
        id: '04',
        name: 'Queiroz'
      },
      {
        id: '02',
        name: 'Carlos'
      },
    ], 
    contribuition: [
      {
        item: 'Laranja',
        guest: '01'
      },
      {
        item: 'Refrigerante',
        guest: '02'
      },
      {
        item: 'Bolo',
        guest: '03'
      },
    ]
  },

]

export default partyMock;