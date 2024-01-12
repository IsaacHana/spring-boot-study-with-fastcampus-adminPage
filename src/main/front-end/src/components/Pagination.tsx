import { useEffect, useState } from "react";

interface PaginationProps {
  pagination: {
    total_pages: number;
    total_elements: number;
    current_elements: number;
  };
  onChangeCurrentElement: (value: number) => void;
}

const Pagination: React.FC<PaginationProps> = ({
  pagination,
  onChangeCurrentElement,
}) => {
  const { total_elements, total_pages, current_elements } = pagination;
  const pageCount = 5;

  const [pageGroup, setPageGroup] = useState<number>(1);

  useEffect(() => {
    if (pageGroup !== Math.ceil((current_elements + 1) / pageCount)) {
      setPageGroup(Math.ceil((current_elements + 1) / pageCount));
    }
  }, [current_elements, pageCount]);

  const pageGroupView = () => {
    let arr = [];
    for (
      let i = pageCount * (pageGroup - 1) + 1;
      i < pageCount * pageGroup + 1;
      i++
    ) {
      if (i > total_pages) break;
      arr.push(
        <div
          key={i}
          onClick={() => onChangeCurrentElement(i)}
          className={`
            flex
            justify-center
            align-middle
            rounded
            px-2
            py-1
            hover:opacity-70
            ${
              current_elements + 1 === i
                ? "border-2 border-stone-50 text-stone-50"
                : ""
            }
              `}
        >
          {i}
        </div>
      );
    }
    return arr;
  };

  return (
    <div className="flex flex-row gap-x-3 my-2 justify-center align-middle text-stone-200 ">
      <div></div>
      <div className="flex flex-row gap-x-3">
        <div>
          <div
            key="next"
            onClick={() => onChangeCurrentElement(current_elements)}
            className="hover:opacity-70"
          >
            <div>prev</div>
          </div>
        </div>
        {pageGroupView()}
        <div>
          <div
            key="next"
            onClick={() => onChangeCurrentElement(current_elements + 2)}
            className="hover:opacity-70"
          >
            <div>next</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Pagination;
