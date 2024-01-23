const Page = () => {
  return (
    <div className="grid grid-cols-4 gap-8 p-8 text-stone-300">
      <div className="flex flex-col">
        <div className="bg-sky-600 text-start p-4 rounded-t-lg">
          <p className="text-3xl font-bold">150</p>
          <p>신규 주문</p>
        </div>
        <div className="bg-sky-900 font-light rounded-b-lg">
          <span>more info</span>
        </div>
      </div>
      <div className="flex flex-col">
        <div className="bg-green-700 text-start p-4 rounded-t-lg">
          <p className="text-3xl font-bold">53</p>
          <p>신규 유저</p>
        </div>
        <div className="bg-green-900 font-light rounded-b-lg">
          <span>more info</span>
        </div>
      </div>
      <div className="flex flex-col">
        <div className="bg-orange-500 text-start p-4 rounded-t-lg">
          <p className="text-3xl font-bold">44</p>
          <p>신규 파트너</p>
        </div>
        <div className="bg-orange-700 font-light rounded-b-lg">
          <span>more info</span>
        </div>
      </div>
      <div className="flex flex-col">
        <div className="bg-indigo-500 text-start p-4 rounded-t-lg">
          <p className="text-3xl font-bold">65</p>
          <p>신규 상품</p>
        </div>
        <div className="bg-indigo-700 font-light rounded-b-lg">
          <span>more info</span>
        </div>
      </div>
    </div>
  );
};

export default Page;
